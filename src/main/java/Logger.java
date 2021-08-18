import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    File logFile;

    public Logger(String logPath) {
        logFile = new File(logPath);
    }

    public void log(String string) {
        StringBuilder logEntry = new StringBuilder();
        try(FileWriter logger = new FileWriter(logFile, true)) {
            logEntry.append(timeFormat.format(new Date()))
                    .append(" : ").append(string).append("\n");
            logger.write(logEntry.toString());
            logger.flush();
        } catch (IOException e) {
            System.out.println("Что-то лог не пишется! ->" + e.getMessage());
        }
    }

}
