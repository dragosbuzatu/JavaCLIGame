import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Read {

    public static void read() {
        JSONParser jsonParser = new JSONParser();
        String path = "C:\\TemaPOOWOM\\accounts.json";

        try (FileReader reader = new FileReader(path)) {
            JSONObject obj = (JSONObject) jsonParser.parse(reader);
            JSONArray accounts = (JSONArray) obj.get("accounts");

            for (int i = 0; i < accounts.size(); i++) {
                Account account = new Account();
                JSONObject information = (JSONObject) accounts.get(i);
                JSONObject credentials = (JSONObject) information.get("credentials");
                String email = (String) credentials.get("email");

                String password = (String) credentials.get("password");

                String name = (String) information.get("name");
                String country = (String) information.get("country");
                Credentials objcredentials = new Credentials();
                objcredentials.setEmail(email);
                objcredentials.setPassword(password);

                JSONArray favorite_games = (JSONArray) information.get("favorite_games");

                ArrayList<String> favGames = new ArrayList<>();
                Iterator<String> iterator = favorite_games.iterator();
                while (iterator.hasNext()) {
                    String game = iterator.next();
                    favGames.add(game);
                    Collections.sort(favGames);
                }
                int maps_completed = Integer.parseInt((String)information.get("maps_completed"));
                account.playerInformation = new Account.Information.Build().realName(name).country(country).playerLogin(objcredentials).favoriteGames(favGames).create();
                account.mapscompleted = maps_completed;
                Game.Init().accountList.add(account);
                JSONArray characters = (JSONArray) information.get("characters");


                account.characters = new ArrayList<>();
                for (int j = 0; j < characters.size(); j++) {
                    JSONObject characterinformation = (JSONObject) characters.get(j);
                    String name2 = (String) characterinformation.get("name");
                    String profession = (String) characterinformation.get("profession");
                    int level = Integer.parseInt((String)characterinformation.get("level"));
                    String experience = String.valueOf(characterinformation.get("experience"));
                    if (profession.equals("Warrior"))
                        account.characters.add(new Warrior(name2,level, Integer.parseInt(experience)));
                    if (profession.equals("Rogue"))
                        account.characters.add(new Rogue(name2,level,Integer.parseInt(experience)));
                    if (profession.equals("Mage"))
                        account.characters.add(new Mage(name2,level,Integer.parseInt(experience)));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONParser jsonParser2 = new JSONParser();
        String path2 = "C:\\TemaPOOWOM\\stories.json";

        try (FileReader reader2 = new FileReader(path2)) {

            JSONObject obj2 = (JSONObject) jsonParser2.parse(reader2);
            JSONArray stories = (JSONArray) obj2.get("stories");
            Game game = Game.Init();

            for (int i = 0; i < stories.size(); i++) {
                JSONObject details = (JSONObject) stories.get(i);
                String type = (String) details.get("type");
                String value = (String) details.get("value");
                if (type.equals("EMPTY"))
                    game.storyMap.get(Cell.cellenum.EMPTY).add(value);
                else if (type.equals("ENEMY"))
                    game.storyMap.get(Cell.cellenum.ENEMY).add(value);
                else if (type.equals("SHOP"))
                    game.storyMap.get(Cell.cellenum.SHOP).add(value);
                else if (type.equals("FINISH"))
                    game.storyMap.get(Cell.cellenum.FINISH).add(value);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
