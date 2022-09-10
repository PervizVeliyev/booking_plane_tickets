package logging;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;

public class Logger {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logger.class);
    static FileAppender fileAppender = new FileAppender();

    public static void info(String message){
        fileAppender.setFile("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\application.log");
        fileAppender.setThreshold(Level.INFO);
        fileAppender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m\n"));
        fileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(fileAppender);
        logger.info(message);
    }

    public static void error(String message) {
        fileAppender.setFile("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\application.log");
        fileAppender.setThreshold(Level.ERROR);
        fileAppender.setLayout(new PatternLayout("%d{dd/MM/yyyy HH-mm-ss} %-5p %c{1}:%L - %m\n"));
        fileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(fileAppender);
        logger.error(message);
    }
}
