package me.all298lie.commands;

import me.all298lie.UserInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.FileUpload;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapleStory {
    private static final DecimalFormat comma = new DecimalFormat("###,###");
    private static final String dir = System.getProperty("user.dir") + "\\.downloads\\userInfo";
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

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
                .setDescription(comma.format(price) + " 메소를 " + count + "명에게 분배금 분배 \n"
                        + "- MVP, PC방 혜택 X: 인당 " + comma.format(price*0.95/count) + " 메소 \n"
                        + "- MVP, PC방 혜택 O: 인당 " + comma.format(price*0.97/count) + " 메소")
                .setFooter(event.getUser().getAsTag())
                .setColor(0x00b4d8)
                .build();
        event.replyEmbeds(embed).queue();
    }

    public static void runUserInfo(SlashCommandInteractionEvent event) {
        String nickname = event.getOption("닉네임").getAsString();
        event.deferReply().queue();

        String url = "https://maple.gg/u/" + nickname;

        driver = new ChromeDriver(options);

        try {
            driver.get(url);
            Thread.sleep(300);

            // 정보갱신 or 최신정보 버튼
            String sync = driver.findElement(By.xpath("//*[@id=\"btn-sync\"]/span")).getText();
            if (!sync.equals("최신정보")) {
                driver.findElement(By.xpath("//*[@id=\"btn-sync\"]")).click();
                Thread.sleep(300);
            }

            // 크롤링
            String worldName = driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/h3/img")).getAttribute("alt");

            // 레벨(###) + 경험치 (###.###)
            String stringLevel = driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/div[1]/ul/li[1]")).getText();
            int level = Integer.parseInt(stringLevel.split("\\.")[1].split("\\(")[0]);
            float exp = Float.parseFloat(stringLevel.split("\\(")[1].split("%")[0]);

            // 직업
            String job = driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/div[1]/ul/li[2]")).getText();

            // 인기도 (###,###)
            int pop = (int) comma.parse(driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/div[1]/ul/li[3]/span[2]")).getText());

            // 길드
            String guild = driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/div[3]/div[1]/a")).getText();

            // 랭킹 (###,###)
            int topTotalRanking = (int) comma.parse(driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/div[3]/div[2]/span")).getText().split(" 위")[0]);
            int topWorldRanking = (int) comma.parse(driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/div[3]/div[3]/span")).getText().split("위")[0]);
            int jobTotalRanking = (int) comma.parse(driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/div[3]/div[5]/span")).getText().split("위")[0]);
            int jobWorldRanking = (int) comma.parse(driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[2]/div[3]/div[4]/span")).getText().split("위")[0]);

            // 무릉 (sec)
            int mulungFloor = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/section/div[1]/div[1]/section/div/div[1]/div/h1")).getText().split(" 층")[0]);
            String[] mulungTimeA = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/section/div[1]/div[1]/section/div/div[1]/div/small")).getText().split("초")[0].split("분 ");
            int mulungTime = Integer.parseInt(mulungTimeA[0]) * 60 + Integer.parseInt(mulungTimeA[1]);

            // 유니온 (###)
            String unionTier = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/section/div[1]/div[3]/section/div/div/div")).getText();
            int unionLevel = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/section/div[1]/div[3]/section/div/div/span")).getText().split("\\.")[1]);

            // 더 시드 (sec)
            int theSeedFloor = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/section/div[1]/div[2]/section/div/div/div/h1")).getText().split(" 층")[0]);
            String[] theSeedTimeA = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/section/div[1]/div[2]/section/div/div/div/small")).getText().split("초")[0].split("분 ");
            int theSeedTime = Integer.parseInt(theSeedTimeA[0]) * 60 + Integer.parseInt(theSeedTimeA[1]);

            // 캐릭터 이미지 저장
            String profileUrl = driver.findElement(By.xpath("//*[@id=\"user-profile\"]/section/div[2]/div[1]/div/div[2]/img")).getAttribute("src");
            String time = format.format(new Date());
            String profileName = time + "_" + nickname + ".png";
            BufferedImage image = ImageIO.read(new URL(profileUrl));
            ImageIO.write(image, "png", new File(dir + File.separator + profileName));

            FileUpload file = FileUpload.fromData(new File(dir + File.separator + profileName), "profile.png");

            // 정보 저장
            UserInfo user = new UserInfo(
                    nickname,
                    worldName, level, exp, job,
                    pop, guild,
                    topTotalRanking, topWorldRanking,
                    jobTotalRanking, jobWorldRanking,
                    mulungFloor, mulungTime,
                    unionTier, unionLevel,
                    theSeedFloor, theSeedTime
                    );

            // 월드로고 주소
            String serverUrl;
            switch (user.getWorldname()) {
                case "오로라" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_4.png";
                case "레드" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_5.png";
                case "이노시스" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_6.png";
                case "유니온" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_7.png";
                case "스카니아" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_8.png";
                case "루나" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_9.png";
                case "제니스" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_10.png";
                case "크로아" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_11.png";
                case "베라" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_12.png";
                case "엘리시움" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_13.png";
                case "아케인" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_14.png";
                case "노바" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_15.png";
                case "리부트", "리부트2" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_2.png";
                case "버닝", "버닝2", "버닝3", "버닝4" -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_16.png";
                default -> serverUrl = "https://ssl.nexon.com/s2/game/maplestory/renewal/common/world_icon/icon_1.png";
            }

            MessageEmbed embed = new EmbedBuilder()
                    .setTitle(user.getUsername() + "님의 정보")
                    .addField("레벨", user.getLevel() + " (" + user.getExp() + "%)",true)
                    .addField("종합 랭킹", user.getTopTotalRanking() + "위 (" + user.getJobTotalRanking() + "위)", true)
                    .addField("월드 랭킹", user.getTopWorldRanking() + "위 (" + user.getJobWorldRanking() + "위)", true)
                    .addField("인기도", comma.format(user.getPopular()), true)
                    .addField("직업", user.getJob(), true)
                    .addField("길드", user.getGuild(), true)
                    .addField("무릉도장", user.getMulungFloor() + "층 (" + user.getMulungTime()/60 + "분 " + user.getMulungTime()%60 + "초)", true)
                    .addField("유니온", user.getUnionTier() + "\n Lv." + user.getUnionLevel(), true)
                    .addField("더 시드", user.getTheSeedFloor() + "층 (" + user.getTheSeedTime()/60 + "분 " + user.getTheSeedTime()%60 + "초)", true)
                    .setThumbnail(serverUrl)
                    .setFooter(event.getUser().getAsTag())
                    .setImage("attachment://profile.png")
                    .setColor(0x00b4d8)
                    .build();

            event.getHook().editOriginalEmbeds(embed).setFiles(file).queue();

        } catch (NoSuchElementException e) {

            event.getHook().editOriginal("[" + nickname + "]은 존재하지 않는 캐릭터입니다.").queue();

        } catch (InterruptedException | IOException | ParseException e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }
    }
}
