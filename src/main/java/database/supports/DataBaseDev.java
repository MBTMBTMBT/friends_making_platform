package database.supports;

import database.daos.LabelsDAO;
import database.daos.PersonDAO;
import database.daos.UserDAO;
import database.dynamicDAOs.*;
import database.exceptions.WrongAttributeNameException;
import database.standarizedTables.*;
import database.tables.Labels;
import database.tables.Person;
import database.tables.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.*;

public class DataBaseDev {
    private static final String[] ALL_TABLES = new String[]{"Administrator", "Books", "Date", "Employee",
            "Event_Location", "Event", "Films", "Food", "Join_Event", "Labels", "Likes", "Location", "Person",
            "phsycological_mentor", "Relationship", "Sports", "User", "User_Photos"};
    private static final String[] LABELS = new String[] {"StdBooks", "StdFilms", "StdFood", "StdLocation", "StdSports"};
    private static final double HETERO_RATE = 0.96;
    // private static final double[] selection = new double[]{1, 0.95, 0.9, 0.85, 0.8, 0.7, 0.6, 0.5, 0.4};
    private static final Map<String, Integer> MAXIMUM_LENGTH = new HashMap<>();

    public static class InitThread extends Thread {
        @Override
        public void run() {
            setDefaultValues();
        }
    }

    public static void initialize() {
        deleteAll();
        setDefaultValues();
        initializeLabels();
        addRandomUsers();
    }

    public static void setDefaultValues() {
        SetDefault.setDefaultMentor();
        SetDefault.setDefaultAdmin();
        SetDefault.setDefaultLabels();
    }

    public static void initializeLabels() {
        List<String> sports = new LinkedList<>();
        sports.add("gym");
        sports.add("ski");
        sports.add("swim");
        sports.add("run");
        sports.add("bicycle");
        sports.add("yoga");
        sports.add("basketball");
        sports.add("football");
        sports.add("skateboard");
        sports.add("table tennis");
        sports.add("tennis");
        sports.add("golf");
        sports.add("billiards");
        sports.add("dance");
        sports.add("street dance");
        sports.add("archery");
        sports.add("fencing");
        sports.add("shooting");
        sports.add("boxing");
        sports.add("Taekwondo");
        sports.add("mountain climbing");
        sports.add("horseback riding");
        sports.add("extreme sports");
        sports.add("volleyball");
        sports.add("sleep");
        MAXIMUM_LENGTH.put("StdSports", sports.size());

        List<String> food = new LinkedList<>();
        food.add("Beijing Roast Duck");
        food.add("Hong Kong morning tea");
        food.add("hot pot");
        food.add("barbecue");
        food.add("spicy pot");
        food.add("lobster");
        food.add("dumplings");
        food.add("braised pork rice");
        food.add("sushi");
        food.add("sashimi");
        food.add("Japanese ramen");
        food.add("Japanese style Teppanyaki");
        food.add("bibimbap");
        food.add("Korean barbecue");
        food.add("Thai food");
        food.add("steak");
        food.add("pasta");
        food.add("Mexican Tacos");
        food.add("pizza");
        food.add("hamburg");
        food.add("French fries");
        food.add("American Fried Chicken");
        food.add("kabob");
        food.add("vegetarian diet");
        food.add("tiramisu");
        food.add("Mousse cake");
        food.add("cheese");
        food.add("chocolate");
        food.add("ice cream");
        MAXIMUM_LENGTH.put("StdFood", food.size());

        List<String> locations = new LinkedList<>();
        locations.add("Chengdu");
        locations.add("Guilin");
        locations.add("Sanya");
        locations.add("Lijiang");
        locations.add("Dali");
        locations.add("Xianggelila");
        locations.add("Xizang");
        locations.add("Gulangyu");
        locations.add("Zhangjiajie");
        locations.add("Jiuzhaigou");
        locations.add("Japan");
        locations.add("Korean");
        locations.add("Jeju");
        locations.add("Bali");
        locations.add("Phuket");
        locations.add("Boracay");
        locations.add("Saipan");
        locations.add("Singapore");
        locations.add("Malaysia");
        locations.add("Thailand");
        locations.add("Philippines");
        locations.add("Indonesia");
        locations.add("India");
        locations.add("Nepal");
        locations.add("Dubai");
        locations.add("Turkey");
        locations.add("Greek");
        locations.add("US");
        locations.add("Canada");
        locations.add("Australia");
        locations.add("Britain");
        locations.add("France");
        locations.add("Italy");
        locations.add("Spain");
        locations.add("Portugal");
        locations.add("Netherlands");
        locations.add("Belgium");
        locations.add("Swiss");
        locations.add("Swedish");
        locations.add("Danish");
        locations.add("Finland");
        locations.add("Czech");
        locations.add("Cuba");
        locations.add("Argentina");
        locations.add("Brazil");
        locations.add("Russia");
        locations.add("England");
        locations.add("Ireland");
        locations.add("Germany");
        MAXIMUM_LENGTH.put("StdLocation", locations.size());

        List<String> films = new LinkedList<>();
        films.add("The Shawshank Redemption");
        films.add("Forrest Gump");
        films.add("Inception");
        films.add("The Matrix");
        films.add("The Legend Of 1900");
        films.add("Spirited Away");
        films.add("Roman Holiday");
        films.add("Schindler's List");
        films.add("Amelie from Montmartre");
        films.add("A Beautiful Mind");
        films.add("Farewell My concubine");
        films.add("Leon");
        films.add("The Godfather");
        films.add("Titanic");
        films.add("Batman");
        films.add("Pulp Fiction");
        films.add("Fight Club");
        films.add("The Upside");
        films.add("Hachiko: A Dog's Story");
        films.add("Gone with the Wind");
        films.add("The Pursuit of Happiness");
        films.add("One Flew Over the Cuckoo's Nest");
        films.add("Scent Of A Woman");
        films.add("Dead Poets Society");
        films.add("The Lord of the Rings");
        films.add("Harry Potter");
        films.add("Infernal Affairs");
        films.add("American Dreams in China");
        films.add("Chungking express");
        films.add("the silence of the lambs");
        MAXIMUM_LENGTH.put("StdFilms", films.size());

        List<String> books = new LinkedList<>();
        books.add("A Tale of Two Cities");
        books.add("Anna Karenina");
        books.add("Crime and Punishment");
        books.add("David Copperfield");
        books.add("Emma");
        books.add("For Whom the Bell Tolls");
        books.add("Gone with the Wind");
        books.add("Great Expectations");
        books.add("Hamlet");
        books.add("Jane Eyre");
        books.add("Jean Christophe");
        books.add("King Lear");
        books.add("Les Miserables");
        books.add("Little Women");
        books.add("Oliver Twist");
        books.add("Pride and Prejudice");
        books.add("Resurrection");
        books.add("Robinson Crusoe");
        books.add("Sense and Sensibility");
        books.add("The Great Gatsby");
        books.add("The Old Man and the Sea");
        books.add("Walden");
        MAXIMUM_LENGTH.put("StdBooks", books.size());

        List<String> works = new LinkedList<>();
        works.add("student");
        works.add("culture/art");
        works.add("entertainment business");
        works.add("finance");
        works.add("medicine");
        works.add("manufacture");
        works.add("IT");
        works.add("media");
        works.add("education/research");
        works.add("sales");
        works.add("others");
        MAXIMUM_LENGTH.put("Works", works.size());

        List<String> activities = new LinkedList<>();
        activities.add("blind date");
        activities.add("picnic");
        activities.add("home party");
        activities.add("barbeque");
        activities.add("role playing detective");
        activities.add("KTV");
        activities.add("hiking");
        activities.add("others");
        MAXIMUM_LENGTH.put("Activities", activities.size());

        List<List<String>> lists = new LinkedList<>();
        lists.add(locations);
        lists.add(works);
        lists.add(food);
        lists.add(films);
        lists.add(books);
        lists.add(sports);
        lists.add(activities);

        int maxLength = lists.get(0).size();
        for (List<String> eachList : lists) {
            if (maxLength < eachList.size()) maxLength = eachList.size();
        }

        List<Iterator<String>> iterators = new LinkedList<>();
        for (List<String> eachList : lists) {
            iterators.add(eachList.iterator());
        }

        for (int i = 0; i < maxLength; i++) {
            List<String> eachRow = new LinkedList<>();
            for (Iterator<String> eachIterator : iterators) {
                if (eachIterator.hasNext()) {
                    eachRow.add(eachIterator.next());
                } else {
                    eachRow.add(null);
                }
            }
            Iterator<String> rowIterator = eachRow.iterator();
            Labels labels = new Labels();
            labels.setSerial(i + 2);
            labels.setLocations(rowIterator.next());
            labels.setWork(rowIterator.next());
            labels.setFood(rowIterator.next());
            labels.setFilm(rowIterator.next());
            labels.setBook(rowIterator.next());
            labels.setSport(rowIterator.next());
            labels.setActivity(rowIterator.next());
            LabelsDAO.saveLabels(labels);
        }
    }

    public static void deleteAll() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            for (String each : ALL_TABLES) {
                transaction = session.beginTransaction();
                session.createSQLQuery("DELETE FROM " + each).executeUpdate();
                transaction.commit();
            }
        } catch (Exception exp) {
            if (transaction != null) {
                transaction.rollback();
            }
            exp.printStackTrace();
        }
    }

    public static void addRandomUsers() {
        Collections.shuffle(NAMES);
        int countUser = 0;
        Random random = new Random();
        for (String eachUserName: NAMES) {
            countUser += 1;
            Person person = new Person();
            User user = new User();

            person.setScreenName(eachUserName);
            person.setpassword("p");
            person.setSurname("TestedUser");
            person.setForename(String.valueOf(countUser));
            person.setGender((Math.random() >= 0.5)? "female": "male");
            if (person.getGender().equals("male")) {
                person.setHeadIcon("static/images/user_male.png");
            } else {
                person.setHeadIcon("static/images/user_female.png");
            }
            PersonDAO.savePerson(person);
            person = PersonDAO.getPersonByScreenName(eachUserName);
            assert person != null;
            int systemID = person.getSystemID();

            user.setDataOfBirth(new Date(100, 3, 28));
            user.setGenderOrientation((Math.random() <= HETERO_RATE)? "hetero": "homosexual");
            user.setWork(1);
            user.setMentorID(1);
            user.setSlogan("I love programming, I love CS!");
            user.setSystemID(systemID);
            UserDAO.saveUser(user);
            user = UserDAO.getUserBySystemID(systemID);
            int userID = user.getUserID();

            UserCommonAttributesDAO commonAttributesDAO;
            // save labels
            for (String attributeName: LABELS) {
                switch (attributeName) {
                    case "StdBooks":
                        commonAttributesDAO = new DynamicBooksDAO();
                        break;
                    case "StdFilms":
                        commonAttributesDAO = new DynamicFilmsDAO();
                        break;
                    case "StdFood":
                        commonAttributesDAO = new DynamicFoodDAO();
                        break;
                    case "StdLocation":
                        commonAttributesDAO = new DynamicLocationDAO();
                        break;
                    case "StdSports":
                        commonAttributesDAO = new DynamicSportsDAO();
                        break;
                    default:
                        throw new WrongAttributeNameException("Attribute " + attributeName + " is not valid");
                }
                int howManyLabels = random.nextInt(9);
                int[] labelNums = new int[MAXIMUM_LENGTH.get(attributeName) - 1];
                for (int i = 2; i <= labelNums.length + 1; i++) labelNums[i - 2] = i;
                List<Integer> labelNumsLst = new LinkedList<>();
                for (int i: labelNums) labelNumsLst.add(i);
                Collections.shuffle(labelNumsLst);
                Iterator<Integer> labelNumIterator = labelNumsLst.iterator();
                for (int i = 0; i < howManyLabels; i++) {
                    int labelNum = labelNumIterator.next();
                    LabelObject label = new StdObject();
                    label.setUserId(userID);
                    label.setLabelId(labelNum);
                    commonAttributesDAO.saveLabel(label);
                }
                commonAttributesDAO.close();  // always close it!
            }
        }
    }

    public static void main(String[] args) {
        initialize();
    }

    // I really can't make out so many names,
    // So I copied these from https://blog.csdn.net/Mingyueyixi/article/details/78022592
    private static final List<String> NAMES = new ArrayList<> (Arrays.asList("Aaron",
            "Abel",
            "Abraham",
            "Adam",
            "Adrian",
            "Aidan",
            "Alva",
            "Alex",
            "Alexander",
            "Alan",
            "Albert",
            "Alfred",
            "Andrew",
            "Andy",
            "Angus",
            "Anthony",
            "Apollo",
            "Arnold",
            "Arthur",
            "August",
            "Austin",
            "Ben",
            "Benjamin",
            "Bert",
            "Benson",
            "Bill",
            "Billy",
            "Blake",
            "Bob",
            "Bobby",
            "Brad",
            "Brandon",
            "Brant",
            "Brent",
            "Brian",
            "Brown",
            "Bruce",
            "Caleb",
            "Cameron",
            "Carl",
            "Carlos",
            "Cary",
            "Caspar",
            "Cecil",
            "Charles",
            "Cheney",
            "Chris",
            "Christian",
            "Christopher",
            "Clark",
            "Cliff",
            "Cody",
            "Cole",
            "Colin",
            "Cosmo",
            "Daniel",
            "Denny",
            "Darwin",
            "David",
            "Dennis",
            "Derek",
            "Dick",
            "Donald",
            "Douglas",
            "Duke",
            "Dylan",
            "Eddie",
            "Edgar",
            "Edison",
            "Edmund",
            "Edward",
            "Edwin",
            "Elijah",
            "Elliott",
            "Elvis",
            "Eric",
            "Ethan",
            "Eugene",
            "Evan",
            "Enterprise",
            "Ford",
            "Francis",
            "Frank",
            "Franklin",
            "Fred",
            "Gabriel",
            "Gaby",
            "Garfield",
            "Gary",
            "Gavin",
            "Geoffrey",
            "George",
            "Gino",
            "Glen",
            "Glendon",
            "Hank",
            "Hardy",
            "Harrison",
            "Harry",
            "Hayden",
            "Henry",
            "Hilton",
            "Hugo",
            "Hunk",
            "Howard",
            "Henry",
            "Ian",
            "Ignativs",
            "Ivan",
            "Isaac",
            "Isaiah",
            "Jack",
            "Jackson",
            "Jacob",
            "James",
            "Jason",
            "Jay",
            "Jeffery",
            "Jerome",
            "Jerry",
            "Jesse",
            "Jim",
            "Jimmy",
            "Joe",
            "John",
            "Johnny",
            "Jonathan",
            "Jordan",
            "Jose",
            "Joshua",
            "Justin",
            "Keith",
            "Ken",
            "Kennedy",
            "Kenneth",
            "Kenny",
            "Kevin",
            "Kyle",
            "Lance",
            "Larry",
            "Laurent",
            "Lawrence",
            "Leander",
            "Lee",
            "Leo",
            "Leonard",
            "Leopold",
            "Leslie",
            "Loren",
            "Lori",
            "Lorin",
            "Louis",
            "Luke",
            "Marcus",
            "Marcy",
            "Mark",
            "Marks",
            "Mars",
            "Marshal",
            "Martin",
            "Marvin",
            "Mason",
            "Matthew",
            "Max",
            "Michael",
            "Mickey",
            "Mike",
            "Nathan",
            "Nathaniel",
            "Neil",
            "Nelson",
            "Nicholas",
            "Nick",
            "Noah",
            "Norman",
            "Oliver",
            "Oscar",
            "Owen",
            "Patrick",
            "Paul",
            "Peter",
            "Philip",
            "Phoebe",
            "Quentin",
            "Randall",
            "Randolph",
            "Randy",
            "Ray",
            "Raymond",
            "Reed",
            "Rex",
            "Richard",
            "Richie",
            "Riley",
            "Robert",
            "Robin",
            "Robinson",
            "Rock",
            "Roger",
            "Ronald",
            "Rowan",
            "Roy",
            "Ryan",
            "Sam",
            "Sammy",
            "Samuel",
            "Scott",
            "Sean",
            "Shawn",
            "Sidney",
            "Simon",
            "Solomon",
            "Spark",
            "Spencer",
            "Spike",
            "Stanley",
            "Steve",
            "Steven",
            "Stewart",
            "Stuart",
            "Terence",
            "Terry",
            "Ted",
            "Thomas",
            "Tim",
            "Timothy",
            "Todd",
            "Tommy",
            "Tom",
            "Thomas",
            "Tony",
            "Tyler",
            "Ultraman",
            "Ulysses",
            "Van",
            "Vern",
            "Vernon",
            "Victor",
            "Vincent",
            "Warner",
            "Warren",
            "Wayne",
            "Wesley",
            "William",
            "Willy",
            "Zack",
            "Zachary",
            "Abigail",
            "Abby",
            "Ada",
            "Adelaide",
            "Adeline",
            "Alexandra",
            "Ailsa",
            "Aimee",
            "Alexis",
            "Alice",
            "Alicia",
            "Alina",
            "Allison",
            "Alyssa",
            "Amanda",
            "Amy",
            "Amber",
            "Anastasia",
            "Andrea",
            "Angel",
            "Angela",
            "Angelia",
            "Angelina",
            "Ann",
            "Anna",
            "Anne",
            "Annie",
            "Anita",
            "Ariel",
            "April",
            "Ashley",
            "Audrey",
            "Aviva",
            "Barbara",
            "Barbie",
            "Beata",
            "Beatrice",
            "Becky",
            "Bella",
            "Bess",
            "Bette",
            "Betty",
            "Blanche",
            "Bonnie",
            "Brenda",
            "Brianna",
            "Britney",
            "Brittany",
            "Camille",
            "Candice",
            "Candy",
            "Carina",
            "Carmen",
            "Carol",
            "Caroline",
            "Carry",
            "Carrie",
            "Cassandra",
            "Cassie",
            "Catherine",
            "Cathy",
            "Chelsea",
            "Charlene",
            "Charlotte",
            "Cherry",
            "Cheryl",
            "Chloe",
            "Chris",
            "Christina",
            "Christine",
            "Christy",
            "Cindy",
            "Claire",
            "Claudia",
            "Clement",
            "Cloris",
            "Connie",
            "Constance",
            "Cora",
            "Corrine",
            "Crystal",
            "Daisy",
            "Daphne",
            "Darcy",
            "Dave",
            "Debbie",
            "Deborah",
            "Debra",
            "Demi",
            "Diana",
            "Dolores",
            "Donna",
            "Dora",
            "Doris",
            "Edith",
            "Editha",
            "Elaine",
            "Eleanor",
            "Elizabeth",
            "Ella",
            "Ellen",
            "Ellie",
            "Emerald",
            "Emily",
            "Emma",
            "Enid",
            "Elsa",
            "Erica",
            "Estelle",
            "Esther",
            "Eudora",
            "Eva",
            "Eve",
            "Evelyn",
            "Fannie",
            "Fay",
            "Fiona",
            "Flora",
            "Florence",
            "Frances",
            "Frederica",
            "Frieda",
            "Flta",
            "Gina",
            "Gillian",
            "Gladys",
            "Gloria",
            "Grace",
            "Grace",
            "Greta",
            "Gwendolyn",
            "Hannah",
            "Haley",
            "Hebe",
            "Helena",
            "Hellen",
            "Henna",
            "Heidi",
            "Hillary",
            "Ingrid",
            "Isabella",
            "Ishara",
            "Irene",
            "Iris",
            "Ivy",
            "Jacqueline",
            "Jade",
            "Jamie",
            "Jane",
            "Janet",
            "Jasmine",
            "Jean",
            "Jenna",
            "Jennifer",
            "Jenny",
            "Jessica",
            "Jessie",
            "Jill",
            "Joan",
            "Joanna",
            "Jocelyn",
            "Joliet",
            "Josephine",
            "Josie",
            "Joy",
            "Joyce",
            "Judith",
            "Judy",
            "Julia",
            "Juliana",
            "Julie",
            "June",
            "Karen",
            "Karida",
            "Katherine",
            "Kate",
            "Kathy",
            "Katie",
            "Katrina",
            "Kay",
            "Kayla",
            "Kelly",
            "Kelsey",
            "Kimberly",
            "Kitty",
            "Lareina",
            "Lassie",
            "Laura",
            "Lauren",
            "Lena",
            "Lydia",
            "Lillian",
            "Lily",
            "Linda",
            "lindsay",
            "Lisa",
            "Liz",
            "Lora",
            "Lorraine",
            "Louisa",
            "Louise",
            "Lucia",
            "Lucy",
            "Lucine",
            "Lulu",
            "Lydia",
            "Lynn",
            "Mabel",
            "Madeline",
            "Maggie",
            "Mamie",
            "Manda",
            "Mandy",
            "Margaret",
            "Mariah",
            "Marilyn",
            "Martha",
            "Mavis",
            "Mary",
            "Matilda",
            "Maureen",
            "Mavis",
            "Maxine",
            "May",
            "Mayme",
            "Megan",
            "Melinda",
            "Melissa",
            "Melody",
            "Mercedes",
            "Meredith",
            "Mia",
            "Michelle",
            "Milly",
            "Miranda",
            "Miriam",
            "Miya",
            "Molly",
            "Monica",
            "Morgan",
            "Nancy",
            "Natalie",
            "Natasha",
            "Nicole",
            "Nikita",
            "Nina",
            "Nora",
            "Norma",
            "Nydia",
            "Octavia",
            "Olina",
            "Olivia",
            "Ophelia",
            "Oprah",
            "Pamela",
            "Patricia",
            "Patty",
            "Paula",
            "Pauline",
            "Pearl",
            "Peggy",
            "Philomena",
            "Phoebe",
            "Phyllis",
            "Polly",
            "Priscilla",
            "Quentina",
            "Rachel",
            "Rebecca",
            "Regina",
            "Rita",
            "Rose",
            "Roxanne",
            "Ruth",
            "Sabrina",
            "Sally",
            "Sandra",
            "Samantha",
            "Sami",
            "Sandra",
            "Sandy",
            "Sarah",
            "Savannah",
            "Scarlett",
            "Selma",
            "Selina",
            "Serena",
            "Sharon",
            "Sheila",
            "Shelley",
            "Sherry",
            "Shirley",
            "Sierra",
            "Silvia",
            "Sonia",
            "Sophia",
            "Stacy",
            "Stella",
            "Stephanie",
            "Sue",
            "Sunny",
            "Susan",
            "Tamara",
            "Tammy",
            "Tanya",
            "Tasha",
            "Teresa",
            "Tess",
            "Tiffany",
            "Tina",
            "Tonya",
            "Tracy",
            "Ursula",
            "Vanessa",
            "Venus",
            "Vera",
            "Vicky",
            "Victoria",
            "Violet",
            "Virginia",
            "Vita",
            "Vivian"));
}
