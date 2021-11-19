package de.pascal;

import de.pascal.utils.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DiscordBot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault(Config.TOKEN).build();
        jda.addEventListener(new DiscordBot());
    }

    public DiscordBot() {

    }


    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[][] bdayCommand = {
                {"Patrick","18.07.1999"," der klassische ADC"},
                {"Evelyn","29.09.1999"," die Pussy Supporterin"},
                {"Pascal","31.10.1999"," der alte Affenkönig"},
                {"Steffen","25.03.2000"," der Bayrer"},
                {"Georg","20.05.2000"," der THICC BOI"},
                {"Luisa","28.05.2000"," die Käsesüchtige"},
                {"Marcel","17.11.2000"," der quoten Türke"},
                {"Nicole","07.02.2001"," die leidenschaftliche Staubsaugerin"},
                {"Rav","11.03.2003"," das Serverbaby"}
        };

        if (event.getMessage().getContentRaw().equalsIgnoreCase("ping!")) {
            event.getChannel().sendMessage("Pong!").queue();
        } else if (event.getMessage().getContentRaw().matches("(?i:.*?lol\\?.*?)")) {
            int zahl = (int) (Math.random() * 100 + 1);
            String respond = "";
            if (zahl <= 80) {
                respond = "||NEIN!!||";
            } else if (zahl <= 90) {
                respond = "|| JA!! ||";
            } else {
                respond = "||Später||";
            }
            event.getChannel().sendMessage(respond).queue();
        } else if (event.getMessage().getContentRaw().matches("(?i:.*rip.*)") && !event.getMessage().getContentRaw().matches("Rest in Rip in Peace")) {
            event.getChannel().sendMessage("Rest in Rip in Peace").queue();
        } else if (event.getMessage().getContentRaw().matches("(^\\.op\\.gg.*)")) {
            String[] name = event.getMessage().getContentRaw().split("\\.op\\.gg", 1);
            name[0] = name[0].substring(7);
            name[0] = name[0].replace(" ", "%20");
            event.getChannel().sendMessage("https://euw.op.gg/summoner/userName=" + name[0]).queue();
        } else if (event.getMessage().getContentRaw().matches("(^\\.op.*)")) {
            String[] name = event.getMessage().getContentRaw().split("\\.op", 1);
            name[0] = name[0].substring(4);
            name[0] = name[0].replace(" ", "%20");
            event.getChannel().sendMessage("https://euw.op.gg/summoner/userName=" + name[0]).queue();
        } else if (event.getMessage().getContentRaw().matches("(^\\.u\\.gg.*)")) {
            String[] name = event.getMessage().getContentRaw().split("\\.u\\.gg", 1);
            name[0] = name[0].substring(6);
            name[0] = name[0].replace(" ", "%20");
            event.getChannel().sendMessage("https://u.gg/lol/champions/" + name[0] + "/build").queue();
        } else if (event.getMessage().getContentRaw().matches("(^\\.u.*)")) {
            String[] name = event.getMessage().getContentRaw().split("\\.u", 1);
            name[0] = name[0].substring(3);
            name[0] = name[0].replace(" ", "%20");
            event.getChannel().sendMessage("https://u.gg/lol/champions/" + name[0] + "/build").queue();
        } else if (event.getMessage().getContentRaw().matches("(?i:\\.random.*)")) {
            String[] name = event.getMessage().getContentRaw().split(" ");

            double result = 69;
            if (name.length > 1) {
                if (name[1].matches("^[+-]?\\d+$")) {
                    double zahl = Double.parseDouble(name[1]);
                    if (name[1] != name[name.length - 1] && name[name.length - 1] != null) {
                        if (name[2].matches("^[+-]?\\d+$")) {
                            double zahl2 = Double.parseDouble(name[2]);
                            if (zahl >= zahl2) {
                                result = ((Math.random() * ((zahl - zahl2) + 1) + zahl2));
                                result = Math.floor(result);
                            } else {
                                return;
                            }
                        }
                    } else {
                        result = (Math.random() * ((zahl - 1) + 1) + 1);
                        result = Math.floor(result);
                    }
                }

                String actual_result = String.valueOf((int) result);
                event.getChannel().sendMessage("Hier ist deine random Zahl:\n" + actual_result).queue();
            } else {
                event.getChannel().sendMessage("Hier ist deine random Zahl:\n69 Nice!").queue();
            }
        } else if (event.getMessage().getContentRaw().matches("(?i:\\.help)") || event.getMessage().getContentRaw().matches("(?i:\\.hilfe)")) {
            event.getChannel().sendMessage(
                    "```" +
                            "\nDie Commands, die der Bot bisher kann:\n" +
                            "\t.op + username oder .op.gg + username:\n\t\t=> Gibt dir einen Link zu dem eingegebenen Usernamen\n" +
                            "\t.u + Champion oder .u.gg + Champion:\n\t\t=> Gibt dir einen Link zum Championbuild\n" +
                            "\t.random + Maxzahl oder .random + Maxzahl + Minzahl:\n\t\t=> Gibt dir eine Zahl zwischen 1 und der eingegebenen Zahl\n\t\toder gibt dir eine Zahl zwischen den eingegebenen Zahlen\n" +
                            "\t.bday + name:\n\t\t=> Gibt dir den Geburtstag der angegebenen Person\n" +
                            "\t.bday alle oder .bday all:\n\t\t=> Gibt dir den geburtstag von den coolen Leuden\n" +
                        "```"
            ).queue();
        } else if (event.getMessage().getAuthor().getName().equalsIgnoreCase("test")) {
            event.getChannel().sendMessage("Ich schreibe nur wenn patrick was scheibt!").queue();
        } else if (event.getMessage().getContentRaw().matches("(?i:\\.bday.*)")) {
            String[] name = event.getMessage().getContentRaw().split(" ");
            if (name[1].equalsIgnoreCase("all") || name[1].equalsIgnoreCase("alle")) {
                String text = "```Alle Geburtstage der coolen Leude:\n";
                for (int i = 0; i < bdayCommand.length; i++) {
                    text = text + bdayCommand[i][0] + " => " + bdayCommand[i][1] + "\n";
                }
                text = text + "```";
                event.getChannel().sendMessage(text).queue();
                return;
            }

            if (name[1].equalsIgnoreCase("next") || name[1].equalsIgnoreCase("nächster")) {
                int next = 0;

                for (int i = 0; i < bdayCommand.length; i++) {
                    String between = getTimeBetween(bdayCommand[i][1]);
                }

                String nextBDay = String.valueOf(next);
            }

            for (int i = 0; i < bdayCommand.length; i++) {
                if (bdayCommand[i][0].equalsIgnoreCase(name[1])) {
                    String[] splittedBDay = bdayCommand[i][1].split("\\.");
                    event.getChannel().sendMessage(
                            bdayCommand[i][0] + bdayCommand[i][2] + " hat am " + bdayCommand[i][1] + " Geburtstag!\n" +
                            getTimeBetween(splittedBDay[0] + "." + splittedBDay[1] + "." + Calendar.getInstance().get(Calendar.YEAR) + " 00:00:00")
                    ).queue();
                }
            }
        } else {
            System.out.println(event.getMessage().getAuthor().getName());
        }
    }


    private String getTimeBetween(String userBDay) {
        long dateNow = System.currentTimeMillis();
        Date userBDayDate = null;

        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        try {
            userBDayDate = formater.parse(userBDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long userBDaymillis = userBDayDate.getTime();
        long dateDiff = userBDaymillis - dateNow;

        if (dateDiff <= 0) {
            for (int i = 0; i < 365; i++) {
                dateDiff += 86400000;
            }
        }

        long days = TimeUnit.MILLISECONDS.toDays(dateDiff);
        dateDiff -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(dateDiff);
        dateDiff -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
        dateDiff -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
        dateDiff -= TimeUnit.SECONDS.toMillis(seconds);
        long millis = dateDiff;

        return "Noch:\nTage:"
                + Long.toString(days) + "\nStunden: "
                + Long.toString(hours) + "\nMinuten: "
                + Long.toString(minutes) + "\nSekunden: "
                + Long.toString(seconds) + "\nMillisekunden: "
                + Long.toString(millis);
    }
}