package gfx;

import app.Editor;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Drawing
{    
    public static void drawImageRotate(Graphics gfx, Image image, Float rotate, ImageObserver observer)
    {
        // NOTE: encorporate the way in which the resize method creates and returns a BufferedImage
        AffineTransform identity = new AffineTransform();
        Graphics2D g2d = (Graphics2D)gfx;
        AffineTransform trans = new AffineTransform();
        trans.setTransform(identity);
        trans.rotate(Math.toRadians(rotate));
        g2d.drawImage(image, trans, observer);
    }
    
    public static void drawImageRotate(Graphics gfx, BufferedImage image, int drawX, int drawY, double rotate)
    {
        AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(rotate), image.getWidth() / 2, image.getHeight() / 2);
        AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        gfx.drawImage(transformOp.filter(image, null), drawX, drawY, null);
    }
    
    public static void drawImageOpaque(Graphics gfx, BufferedImage image, int posX, int posY, float alpha)
    {
        // Set Opacity
        Graphics2D g2D = (Graphics2D) gfx;
        AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        g2D.setComposite(composite);

        // Draw Image
        g2D.drawImage(image, posX, posY, null);

        // Clear Opacity
        composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
        g2D.setComposite(composite);
    }

    public static void fadeScreen(Graphics gfx)
    {
        fadeScreen(gfx, 0.75f);
    }

    public static void fadeScreen(Graphics gfx, float opacity)
    {
        Drawing.drawImageOpaque(gfx, getImage("drawing/fadeBlack.png"), 0, 0, opacity);
    }

    public static void fillScreen(Graphics gfx)
    {
        fillScreen(gfx, 0, 0, 0);
    }

    public static void fillScreen(Graphics gfx, String colour)
    {
        gfx.setColor(Theme.getColour(colour));
        gfx.fillRect(0, 0, Editor.getAppWidth(), Editor.getAppHeight());
    }

    public static void fillScreen(Graphics gfx, int colorR, int colorG, int colorB)
    {
        gfx.setColor(getColorRGB(colorR, colorG, colorB));
        gfx.fillRect(0, 0, Editor.getAppWidth(), Editor.getAppHeight());
    }

    public static BufferedImage flipImage(BufferedImage image)
    {
        AffineTransform transform1 = AffineTransform.getScaleInstance(-1, 1);
        transform1.translate(-image.getWidth(null), 0);
        AffineTransformOp transform2 = new AffineTransformOp(transform1, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return transform2.filter(image, null);
    }

    public static Color getColorRGB(int r, int g, int b)
    {
        float hsb[] = Color.RGBtoHSB(r,g,b,null);
        return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
    }

    public static BufferedImage getImage(String filepath)
    {
        return getImageFile(Editor.getPath() + "res/" + filepath);
    }

    public static BufferedImage getImageFile(String filepath)
    {
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(new File(filepath));
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.out.println(filepath);
        }
        return image;
    }
    
    public static BufferedImage getImageGrayscale(String filepath)
    {
        return getImageGrayscale(getImage(filepath));
    }
    
    public static BufferedImage getImageGrayscale(BufferedImage image)
    {
        return getImageGrayscale(image, image.getWidth(), image.getHeight());
    }
        
    public static BufferedImage getImageGrayscale(BufferedImage image, int width, int height)
    {
        BufferedImage gray = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        Graphics gfx = gray.getGraphics();
        gfx.drawImage(image, 0, 0, null);
        gfx.dispose();
        return gray;
    }
    
    public static int getTextWidth(Graphics g, String text)
    {
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics().getFontMetrics(g.getFont()).stringWidth(text);
    }

    public static BufferedImage resize(BufferedImage imgOld, int newW, int newH)
    { 
        Image imgTemp = imgOld.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage imgNew = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = imgNew.createGraphics();
        g2d.drawImage(imgTemp, 0, 0, null);
        g2d.dispose();
        return imgNew;
    }

    public static void write(Graphics gfx, String write, int posX, int posY)
    {
        write(gfx, write, posX, posY, "LEFT", false);
    }

    public static void write(Graphics gfx, String write, int posX, int posY, String align)
    {
        write(gfx, write, posX, posY, align, false);
    }

    public static void write(Graphics gfx, String write, int posX, int posY, String align, boolean shadow)
    {
        if(align == "CENTER") {posX = posX - (getTextWidth(gfx, write) / 2);}
        if(align == "RIGHT") {posX = posX - getTextWidth(gfx, write);}
        if(shadow)
        {
            Color rgb = gfx.getColor();
            gfx.setColor(Theme.getColour("TEXTSHADOW"));
            gfx.drawString(write, posX + 3, posY + 2);
            gfx.setColor(rgb);
        }
        gfx.drawString(write, posX, posY);
    }
    
    public static void writeTextShadow(Graphics gfx, String writeText, int posX, int posY)
    {
        writeTextShadow(gfx, writeText, posX, posY, Editor.getThemeColour("SHADOW"));
    }
    
    public static void writeTextShadow(Graphics gfx, String writeText, int posX, int posY, Color shadow)
    {
        Color rgb = gfx.getColor();
        gfx.setColor(shadow);
        gfx.drawString(writeText, posX + 1, posY + 1);
        gfx.setColor(rgb);
        gfx.drawString(writeText, posX, posY);
    }
    
}