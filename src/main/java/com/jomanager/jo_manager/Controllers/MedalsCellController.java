package com.jomanager.jo_manager.Controllers;

import com.jomanager.jo_manager.Models.Medal;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class MedalsCellController implements Initializable {
    public ImageView flag_img;
    public Label name_lbl;
    public Label golden_lbl;
    public ImageView golden_imgView;
    public Label silver_lbl;
    public ImageView silver_imgView;
    public Label bronze_lbl;
    public ImageView bronze_imgView;
    public Label all_medals_lbl;
    public ImageView all_medals_imgView;

    private final Medal medal;

    public MedalsCellController(Medal medal) {
        this.medal = medal;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setFlag_img();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        name_lbl.textProperty().bind(medal.getAthleteName());
        golden_lbl.textProperty().bind(medal.getGoldenMedals_amount().asString());
        silver_lbl.textProperty().bind(medal.getSilverMedals_amount().asString());
        bronze_lbl.textProperty().bind(medal.getBronzeMedals_amount().asString());
        golden_imgView.setImage(new Image(String.valueOf(getClass().getResource("/Images/gold.png"))));
        silver_imgView.setImage(new Image(String.valueOf(getClass().getResource("/Images/silver.png"))));
        bronze_imgView.setImage(new Image(String.valueOf(getClass().getResource("/Images/bronze.png"))));
        all_medals_imgView.setImage(new Image(String.valueOf(getClass().getResource("/Images/all.png"))));

        all_medals_lbl.textProperty().bind(medal.getMedals_amount().asString());
    }

    private void setFlag_img() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("src/main/resources/Images/codes.json"));
        JSONObject jo = (JSONObject) obj;
        jo.keySet().forEach(keyStr ->
        {
            Object keyvalue = jo.get(keyStr);
            if (keyvalue.equals(medal.getFlagCountry().getValue())) {
                try {
                    URL url = new URL("https://flagcdn.com/16x12/"+keyStr+".png");
                    BufferedImage bufferedImage = ImageIO.read(url);
                    flag_img.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });

    }
}
