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
    private final int mulungFloor;
    private final int mulungTime;
    private final String unionTier;
    private final int unionLevel;
    private final int theSeedFloor;
    private final int theSeedTime;

    public UserInfo(String username,
                    String worldname, int level, float exp, String job,
                    int popular, String guild,
                    int topTotalRanking, int topWorldRanking,
                    int jobTotalRanking, int jobWorldRanking,
                    int mulungFloor, int mulungTime,
                    String unionTier, int unionLevel,
                    int theSeedFloor, int theSeedTime) {
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
        this.mulungFloor = mulungFloor;
        this.mulungTime = mulungTime;
        this.unionTier = unionTier;
        this.unionLevel = unionLevel;
        this.theSeedFloor = theSeedFloor;
        this.theSeedTime = theSeedTime;
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

    public int getMulungFloor() {
        return mulungFloor;
    }

    public int getMulungTime() {
        return mulungTime;
    }

    public String getUnionTier() {
        return unionTier;
    }

    public int getUnionLevel() {
        return unionLevel;
    }

    public int getTheSeedFloor() {
        return theSeedFloor;
    }

    public int getTheSeedTime() {
        return theSeedTime;
    }
}
