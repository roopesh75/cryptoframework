package ui.utils;


import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import ui.support.Config;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by vevinmoza on 10/21/17.
 */
public class ScreenShotEditor {
    private static final Logger logger = Logger.getLogger(ScreenShotEditor.class);
    private static final GraphicsConfiguration config1 =
            GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    public static ScreenShotEditor newInstance(){
        return new ScreenShotEditor();
    }

    public String editScreenShot(File fileName,String stepDescription,Object stepNumber) throws Exception{

        BufferedImage ALTERED;
        String description="";
        try
        {
            BufferedImage image= ImageIO.read(fileName);
            int bannerSize = 800;
            ALTERED = config1.createCompatibleImage(
                    image.getWidth(),
                    image.getHeight() + bannerSize);
            Graphics2D g2 = ALTERED.createGraphics();
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, ALTERED.getWidth(), bannerSize / 2);
            g2.drawImage(image, 0, bannerSize / 2, null);
            g2.fillRect(0, image.getHeight() + bannerSize / 2, ALTERED.getWidth(), bannerSize / 2);

            g2.setFont(new Font("Lucida Sans", Font.BOLD, 15));

            if(NumberUtils.isNumber(stepNumber.toString())) {
                g2.setColor(Color.BLUE);
                g2.drawString("StepNumber: " + Integer.parseInt(stepNumber.toString()), 10, 50);
                g2.drawString("Description: ", 10, 100);
                g2.drawString(stepDescription, 10, 150);
                g2.drawString("TestScriptMethodName: ", 10, 200);
                g2.drawString(Thread.currentThread().getStackTrace()[3].getMethodName(), 10, 250);
                g2.drawString("CompleteTestScriptName: " + Thread.currentThread().getStackTrace()[3].getClassName(), 10, 300);
            }else {
                g2.setColor(Color.BLACK);
                g2.drawString("StepNumber: " + stepNumber.toString(), 10, 50);
                //g2.drawString("Loc Picked From : " + fileName.getCanonicalPath().split("Run_")[1], 10, 50);
                g2.drawString("TestScriptMethodName: " +Thread.currentThread().getStackTrace()[5].getMethodName(), 10, 150);
                int pageNameIndex=Thread.currentThread().getStackTrace()[4].getClassName().split("\\.").length-1;
                description= Thread.currentThread().getStackTrace()[4].getClassName().split("\\.")[pageNameIndex]+" "+stepDescription+
                        " "+Thread.currentThread().getStackTrace()[3].getMethodName();
                g2.drawString("Description: " + description, 10, 100);
                g2.drawString("CompleteTestScriptName: " + Thread.currentThread().getStackTrace()[5].getClassName(), 10, 200);
            }

            g2.drawString("RunNumber: " + "1", 10, 350);
            g2.drawString("UserName: " + System.getProperty("user.name"), 10, bannerSize / 2 + image.getHeight() + 50);
            g2.drawString("MachineName: " + InetAddress.getLocalHost().getHostName(), 10, bannerSize / 2 + image.getHeight() + 100);
            g2.drawString("Date: " + new SimpleDateFormat("dd/MMM/yyyy HH:mm:ss").format(new Date()), 10, bannerSize / 2 + image.getHeight() + 150);
            g2.drawString("TimeZone: " + displayTimeZone(TimeZone.getDefault()), 10, bannerSize / 2 + image.getHeight() + 200);
            g2.drawString("Environment: " + Config.getApplicationUrl(), 10, bannerSize / 2 + image.getHeight() + 250);
            g2.drawString("Browser: " + Config.getBrowser(), 10, bannerSize / 2 + image.getHeight() + 300);
            g2.drawString("Version: " + Config.getBrowserVersion(), 10, bannerSize / 2 + image.getHeight() + 350);
            g2.dispose();
            // Save image
            ImageIO.write(ALTERED, "PNG", fileName);
        } catch (IOException e) {
            logger.info("ThreadID: "+Thread.currentThread().getId()+"  "+Thread.currentThread().getName()+" "+"FILE NAME:"+fileName);
            e.printStackTrace();
        }

        return description;
    }
    public static String markUp(String stepDescription,String fileName){
        return "<span ng-bind-html=\"trust(log.details)\" class=\"ng-binding\">"+stepDescription+"</span>\n" +
                "                    <!-- ngIf: log.media.length>0 --><span ng-if=\"log.media.length>0\" class=\"ng-scope\">\n" +
                "                        <!-- ngRepeat: media in log.media --><span ng-repeat=\"media in log.media\" class=\"ng-scope\">\n" +
                "<!-- ngIf: media.mediaType=='img' --><img width=\"10%\" class=\"report-img ng-scope\" data-featherlight=\"http://"+Config.getSCREENSHOTSERVER()+"/"+fileName+"\" " +
                "src=\"http://"+Config.getSCREENSHOTSERVER()+"/"+fileName+"\" " +
                "ng-if=\"media.mediaType=='img'\"><!-- end ngIf: media.mediaType=='img' -->\n" +
                "                        </span><!-- end ngRepeat: media in log.media -->\n" +
                "                    </span><!-- end ngIf: log.media.length>0 -->\n" +
                "                </td></span>\n" +
                "                    <!-- ngIf: log.media.length>0 -->";

    }
    private static String displayTimeZone(TimeZone tz) {

        long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
        long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
                - TimeUnit.HOURS.toMinutes(hours);
        // avoid -4:-30 issue
        minutes = Math.abs(minutes);

        String result = "";
        if (hours > 0) {
            result = String.format("(GMT+%d:%02d) %s", hours, minutes, tz.getID());
        } else {
            result = String.format("(GMT%d:%02d) %s", hours, minutes, tz.getID());
        }

        return result;

    }
}
