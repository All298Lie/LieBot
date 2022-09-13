package me.all298lie;

public class UserInfo {
    private final String username;
    private final String worldname;
    private final int level;
    private final float exp;
    private final String job;
    private final int popular;
    private final String guild;
    private final int topTotalRanking;
    private final int topWorldRanking;
    private final int jobTotalRanking;
    private final int jobWorldRanking;
    private boolean isMulung = false;
    private int mulungFloor;
    private int mulungTime;
    private boolean isUnion = false;
    private String unionTier;
    private int unionLevel;
    private boolean isTheSeed = false;
    private int theSeedFloor;
    private int theSeedTime;

    public UserInfo(String username,
                    String worldname, int level, float exp, String job,
                    int popular, String guild,
                    int topTotalRanking, int topWorldRanking,
                    int jobTotalRanking, int jobWorldRanking
                    ) {
        this.username = username;
        this.worldname = worldname;

        this.level = level;
        this.exp = exp;

        this.job = job;
        this.popular = popular;
        this.guild = guild;

        this.topTotalRanking = topTotalRanking;
        this.topWorldRanking = topWorldRanking;
        this.jobTotalRanking = jobTotalRanking;
        this.jobWorldRanking = jobWorldRanking;

        this.isMulung = true;
        this.mulungFloor = mulungFloor;
        this.mulungTime = mulungTime;

        this.isUnion = true;
        this.unionTier = unionTier;
        this.unionLevel = unionLevel;

        this.isTheSeed = true;
        this.theSeedFloor = theSeedFloor;
        this.theSeedTime = theSeedTime;
    }

    public void setMulung(int floor, int time) {
        this.isMulung = true;
        this.mulungFloor = floor;
        this.mulungTime = time;
    }

    public void setUnion(String tier, int level) {
        this.isUnion = true;
        this.unionTier = tier;
        this.unionLevel = level;
    }

    public void setTheSeed(int floor, int time) {
        this.isTheSeed = true;
        this.theSeedFloor = floor;
        this.theSeedTime = time;
    }

    public String getUsername() {
        return username;
    }

    public String getWorldname() {
        return worldname;
    }

    public int getLevel() {
        return level;
    }

    public float getExp() {
        return exp;
    }

    public String getJob() {
        return job;
    }

    public int getPopular() {
        return popular;
    }

    public String getGuild() {
        return guild;
    }

    public int getTopTotalRanking() {
        return topTotalRanking;
    }

    public int getTopWorldRanking() {
        return topWorldRanking;
    }

    public int getJobTotalRanking() {
        return jobTotalRanking;
    }

    public int getJobWorldRanking() {
        return jobWorldRanking;
    }

    public boolean isMulung() {
        return isMulung;
    }

    public int getMulungFloor() {
        return isMulung ? mulungFloor : -1;
    }

    public int getMulungTime() {
        return isMulung ? mulungTime : -1;
    }

    public boolean isUnion() {
        return isUnion;
    }

    public String getUnionTier() {
        return isUnion ? unionTier : null;
    }

    public int getUnionLevel() {
        return isUnion ? unionLevel : -1;
    }

    public boolean isTheSeed() {
        return isTheSeed;
    }

    public int getTheSeedFloor() {
        return isTheSeed ? theSeedFloor : -1;
    }

    public int getTheSeedTime() {
        return isTheSeed ? theSeedTime : -1;
    }
}
