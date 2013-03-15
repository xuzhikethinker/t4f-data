package aos.format.ascii;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.alcibiade.asciiart.coord.TextBoxSize;
import org.alcibiade.asciiart.raster.CharacterRaster;
import org.alcibiade.asciiart.raster.ExtensibleCharacterRaster;
import org.alcibiade.asciiart.raster.Raster;
import org.alcibiade.asciiart.raster.RasterContext;
import org.alcibiade.asciiart.raster.image.ImageRasterizer;
import org.alcibiade.asciiart.raster.image.LetterImage;
import org.alcibiade.asciiart.widget.LabelWidget;
import org.alcibiade.asciiart.widget.TextWidget;
import org.junit.Assert;
import org.junit.Test;

public class ExtensibleCharacterRasterTest {

    @Test
    public void testLabelWithExtensibleRaster() {

        TextWidget widget = new LabelWidget("Hello world !");
        Raster raster = new ExtensibleCharacterRaster();
        RasterContext rc = new RasterContext(raster);
        widget.render(rc);
        String rasterString = raster.toString();
        System.out.println(rasterString);
        Assert.assertTrue(rasterString.startsWith("Hello world !"));
    }

    @Test
    public void testNew() throws IOException {
        Dimension size = new Dimension(64, 128);
        LetterImage letterImage = new LetterImage(size, 'X');
        System.out.println(letterImage.toString());
    }

    @Test
    public void testPictureRasterization() throws IOException {
        rasterizePicture("org/alcibiade/asciiart/raster/image/Sample_BlackCircle.png");
        rasterizePicture("org/alcibiade/asciiart/raster/image/Sample_Gradient_H.png");
        rasterizePicture("org/alcibiade/asciiart/raster/image/Sample_Gradient_V.png");
    }

    private void rasterizePicture(String imageFileName) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL circleImageURL = classLoader.getResource(imageFileName);
        BufferedImage circleImage = ImageIO.read(circleImageURL);
        CharacterRaster raster = new ExtensibleCharacterRaster();
        ImageRasterizer.rasterize(circleImage, new RasterContext(raster), new TextBoxSize(32, 10));
        // System.out.println(raster);
    }

}
