package sample;

import javafx.scene.image.Image;

import java.io.FileInputStream;

/**
 * Created by nazoorahamed on 11/13/17.
 */
public class Symbol implements ISymbol {
    private Image image;
    private int value = 0;

    protected Symbol(int value, Image image) {
        setImage(image);
        setValue(value);
    }




    @Override
    public void setImage(Image image) {
      this.image = image;


    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setValue(int v) {
        this.value = v;
    }

    @Override
    public int getValue() {
        return value ;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "image=" + image +
                ", value=" + value +
                '}';
    }
}
