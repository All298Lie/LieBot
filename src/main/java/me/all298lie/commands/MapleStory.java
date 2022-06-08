package me.all298lie.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapleStory {
    private static final DecimalFormat money = new DecimalFormat("###,###");
    private static final String dir = System.getProperty("user.dir") + "\\.downloads\\userInfo";

    private static WebDriver driver;
    private static ChromeOptions options;

    static {
        Path path = Paths.get(System.getProperty("user.dir"));
        System.setProperty("webdriver.chrome.driver", path + "\\chromedriver.exe");

        options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("lang=ko_KR");
        options.addArguments("disable-defult-apps");
        options.addArguments("disable-popup-blocking");

        Map<String, Object> p = new HashMap<>();
        p.put("download.default_directory", System.getProperty("user.dir") + File.separator + ".downloads" + File.separator + "userInfo");
        options.setExperimentalOption("prefs", p);
    }
    public static void runDistributionMoney(SlashCommandInteractionEvent event) {
        long price = event.getOption("금액").getAsLong();
        int count = event.getOption("인원_수").getAsInt();
        MessageEmbed embed = new EmbedBuilder()
                .setTitle(":scales: 메이플 분배금 계산기")
                .setDescription(money.format(price) + " 메소를 " + count + "명에게 분배금 분배 \n"
                        + "- MVP, PC방 혜택 X: 인당 " + money.format(price*0.95/count) + " 메소 \n"
                        + "- MVP, PC방 혜택 O: 인당 " + money.format(price*0.97/count) + " 메소")
                .setFooter(event.getUser().getAsTag())
                .setColor(0x00b4d8)
                .build();
        Message message = new MessageBuilder(embed).build();
        event.reply(message).queue();
    }

    public static void runUserInfo(SlashCommandInteractionEvent event) {
        String nickname = event.getOption("닉네임").getAsString();
        event.deferReply().queue();

        String url = "https://maple.gg/u/" + nickname;

        driver = new ChromeDriver(options);

        try {
            driver.get(url);

            // 정보갱신 or 최신정보 버튼
            String sync = driver.findElement(By.xpath("//*[@id=\"btn-sync\"]/span")).getText();
            if (!sync.equals("최신정보")) {
                event.getHook().sendMessage("플레이어의 정보를 갱신 중...").queue();
                driver.findElement(By.xpath("//*[@id=\"btn-sync\"]")).click();
                Thread.sleep(300);
            }

            // 프로필 저장 버튼
            driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/div[4]/button[3]")).sendKeys(Keys.ENTER);
            Thread.sleep(300);

            // 저장하기 버튼
           driver.findElement(By.xpath("//*[@id=\"btn-save\"]")).sendKeys(Keys.ENTER);
            Thread.sleep(2500);

            File file = getTheNewestFile("png");
            File newFile = new File(dir + File.separator + nickname + ".png");
            file.renameTo(newFile);

            event.getHook().sendFile(newFile).queue();

        } catch (NoSuchElementException e) {

            event.getHook().sendMessage("[" + nickname + "]은 존재하지 않는 캐릭터터입니다.").queue();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }

    public static File getTheNewestFile(String ext) {
        File theNewestFile = null;
        File directory = new File(dir);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = directory.listFiles(fileFilter);

        if (files.length > 0) {
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
        }

        return theNewestFile;
    }
}
