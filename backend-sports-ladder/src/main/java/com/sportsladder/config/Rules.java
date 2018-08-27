package com.sportsladder.config;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rule")
public class Rules {

    /**
     * Designator for rules pertaining to challenges
     */
    private ChallengeRule challengeRule;

    public ChallengeRule getChallengeRule() {
        return challengeRule;
    }

    public void setChallengeRule(ChallengeRule challengeRule) {
        this.challengeRule = challengeRule;
    }

    public static class ChallengeRule {

        /**
         * The number of ranks above a player that is within challenging range
         */
        @NotBlank
        private int eligibleRankRange;

        /**
         * The number of days that a challenge can remain active before being
         * deprecated/auto-resolved
         */
        @NotBlank
        private int eligibleDuration;

        /**
         * The number of days before a defender may be re-challenged by the
         * same challenger
         */
        @NotBlank
        private int repeatChallengeGracePeriod;

        public int getEligibleRankRange() {
            return eligibleRankRange;
        }

        public void setEligibleRankRange(int eligibleRankRange) {
            this.eligibleRankRange = eligibleRankRange;
        }

        public int getEligibleDuration() {
            return eligibleDuration;
        }

        public void setEligibleDuration(int eligibleDuration) {
            this.eligibleDuration = eligibleDuration;
        }

        public int getRepeatChallengeGracePeriod() {
            return repeatChallengeGracePeriod;
        }

        public void setRepeatChallengeGracePeriod(int repeatChallengeGracePeriod) {
            this.repeatChallengeGracePeriod = repeatChallengeGracePeriod;
        }
    }
}
