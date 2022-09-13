package me.all298lie;

public class UserInfo {
    private final String username;
    private final String worldname;
    private final int level;
    private final float exp;
    private final String job;
    private final int popular;
    private final String guild;
    private final String topRank;
    private final String jobRank;
    private final String mulungFloor;
    private final String mulungTime;
    private final String unionTier;
    private final String unionLevel;
    private final String theSeedFloor;
    private final String theSeedTime;

    public UserInfo(String username,
                    String worldname, int level, float exp, String job,
                    int popular, String guild, String topRank, String jobRank,
                    String mulungFloor, String mulungTime,
                    String unionTier, String unionLevel,
                    String theSeedFloor, String theSeedTime) {
        this.username = username;
        this.worldname = worldname;
        this.level = level;
        this.exp = exp;
        this.job = job;
        this.popular = popular;
        this.guild = guild;
        this.topRank = topRank;
        this.jobRank = jobRank;
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

    public String getTopRank() {
        return topRank;
    }

    public String getJobRank() {
        return jobRank;
    }

    public String getMulungFloor() {
        return mulungFloor;
    }

    public String getMulungTime() {
        return mulungTime;
    }

    public String getUnionTier() {
        return unionTier;
    }

    public String getUnionLevel() {
        return unionLevel;
    }

    public String getTheSeedFloor() {
        return theSeedFloor;
    }

    public String getTheSeedTime() {
        return theSeedTime;
    }
}
