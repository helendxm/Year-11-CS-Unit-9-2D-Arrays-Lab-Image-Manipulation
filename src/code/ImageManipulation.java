package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        APImage img = new APImage("cyberpunk2077.jpg");
        img.draw();
        //grayScale("cyberpunk2077.jpg");
        //blackAndWhite("cyberpunk2077.jpg");
        //edgeDetection("cyberpunk2077.jpg", 20);
        //reflectImage("cyberpunk2077.jpg");
        //rotateImage("cyberpunk2077.jpg");
    }

    /** CHALLENGE ONE: Grayscale
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        int width = img.getWidth();
        int height = img.getHeight();

        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++){
                Pixel pixel = img.getPixel(i,j);
                int av = getAverageColour(pixel);
                pixel.setRed(av);
                pixel.setGreen(av);
                pixel.setBlue(av);
            }
        }
        img.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel The Pixel object for which to calculate the average color value.
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
    }

    /** CHALLENGE TWO: Black and White*
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image*
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage img = new APImage(pathOfFile);
        int width = img.getWidth();
        int height = img.getHeight();
        for (int i=0; i<width; i++){
            for (int j=0; j<height; j++){
                Pixel pixel = img.getPixel(i,j);
                int av = getAverageColour(pixel);
                if (av < 128){
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                }
                else {
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);
                }
            }
        }
        img.draw();
    }

    /** CHALLENGE Three: Edge Detection*
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.*
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.*
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */

    public static void edgeDetection(String pathToFile, int threshold) {
        APImage img = new APImage(pathToFile);
        APImage img2 = new APImage(img.getWidth(), img.getHeight());

        for (int i=1; i<img.getWidth()-1; i++){
            for (int j=0; j<img.getHeight()-1; j++){
                Pixel nowPixel = img.getPixel(i,j);
                Pixel leftPixel = img.getPixel(i-1,j);
                Pixel downPixel = img.getPixel(i,j+1);

                int nowAv = (nowPixel.getRed() + nowPixel.getGreen() + nowPixel.getBlue())/3;
                int leftAv = (leftPixel.getRed() + leftPixel.getGreen() + leftPixel.getBlue())/3;
                int downAv = (downPixel.getRed() + downPixel.getGreen() + downPixel.getBlue())/3;

                if (Math.abs(nowAv-leftAv)>threshold || Math.abs(nowAv-downAv)>threshold){
                    img2.setPixel(i,j,new Pixel(0,0,0));
                }
                else {
                    img2.setPixel(i,j,new Pixel(255,255,255));
                }
            }
        }
        img2.draw();
    }

    /** CHALLENGE Four: Reflect Image*
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage img = new APImage(pathToFile);
        APImage img2 = new APImage(img.getWidth(), img.getHeight());
        for (int i=0; i<img.getWidth()-1; i++){
            for (int j=0; j<img.getHeight()-1; j++){
                Pixel pixel = img.getPixel(i,j);
                img2.setPixel(img.getWidth()-1-i, j, pixel);
            }
        }
        img2.draw();
    }

    /** CHALLENGE Five: Rotate Image*
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE*
     *  */
    public static void rotateImage(String pathToFile) {
        APImage img = new APImage(pathToFile);
        APImage img2 = new APImage(img.getHeight(), img.getWidth());

        for (int i=0; i<img.getWidth(); i++){
            for (int j=0; j<img.getHeight(); j++){
                Pixel pixel = img.getPixel(i,j);
                img2.setPixel(img.getHeight()-1-j, i, pixel);
            }
        }
        img2.draw();
    }
}
