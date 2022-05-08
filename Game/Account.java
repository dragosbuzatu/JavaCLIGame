import java.util.*;

public class Account {
    Information playerInformation;
    List<Character> characters = new ArrayList<>();
    int mapscompleted;
    public static class Information {
        Credentials playerLogin;
        String country;
        String realname;
        ArrayList<String> favoriteGames;

        private Information(Build build) {
            this.playerLogin = build.playerLogin;
            this.country = build.country;
            this.realname = build.realName;
            this.favoriteGames = build.favoriteGames;
        }
        public static class Build {
            Credentials playerLogin;
            String country;
            String realName;
            ArrayList<String> favoriteGames = new ArrayList<>();

            public Information create() {

                return new Information(this);
            }

            public Build playerLogin(final Credentials playerLogin) {
                this.playerLogin = playerLogin;
                return this;
            }

            public Build country(final String country) {
                this.country = country;
                return this;
            }

            public Build realName(final String realName) {
                this.realName = realName;
                return this;
            }

            public Build favoriteGames(final ArrayList<String> favoriteGames) {
                this.favoriteGames = favoriteGames;
                return this; 
                
            }
        }
    }
}
