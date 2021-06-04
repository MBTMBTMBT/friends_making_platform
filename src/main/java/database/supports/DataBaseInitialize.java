package database.supports;

import database.daos.LabelsDAO;
import database.tables.Labels;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class DataBaseInitialize {
    public static class InitThread extends Thread {
        @Override
        public void run() {
            setDefaultValues();
        }
    }

    public static void initialize() {
        setDefaultValues();
        initializeLabels();
    }

    public static void setDefaultValues() {
        SetDefault.setDefaultMentor();
        SetDefault.setDefaultAdmin();
        SetDefault.setDefaultLabels();
    }

    public static void initializeLabels() {
        List<String> sports = new LinkedList<>();
        sports.add("gym"); sports.add("ski");
        sports.add("swim"); sports.add("gym");
        sports.add("bicycle"); sports.add("yoga");
        sports.add("basketball"); sports.add("football");
        sports.add("skateboard"); sports.add("table tennis");
        sports.add("tennis"); sports.add("golf");
        sports.add("billiards"); sports.add("dance");
        sports.add("street dance"); sports.add("archery");
        sports.add("fencing"); sports.add("shooting");
        sports.add("boxing"); sports.add("Taekwondo");
        sports.add("mountain climbing"); sports.add("horseback riding");
        sports.add("extreme sports"); sports.add("football");
        sports.add("skateboard"); sports.add("volleyball");
        sports.add("sleep");

        List<String> food = new LinkedList<>();
        food.add("Beijing Roast Duck"); food.add("Hong Kong morning tea");
        food.add("hot pot"); food.add("barbecue");
        food.add("spicy pot"); food.add("lobster");
        food.add("dumplings"); food.add("braised pork rice");
        food.add("sushi"); food.add("sashimi");
        food.add("Japanese ramen"); food.add("apanese style Teppanyaki");
        food.add("bibimbap"); food.add("Korean barbecue");
        food.add("Thai food"); food.add("steak");
        food.add("pasta"); food.add("Mexican Tacos");
        food.add("pizza"); food.add("hamburg");
        food.add("French fries"); food.add("American Fried Chicken");
        food.add("kabob"); food.add("vegetarian diet");
        food.add("tiramisu"); food.add("Mousse cake");
        food.add("cheese"); food.add("chocolate");
        food.add("ice cream");

        List<String> locations = new LinkedList<>();
        locations.add("Chengdu"); locations.add("Guilin");
        locations.add("Sanya"); locations.add("Lijiang");
        locations.add("Dali"); locations.add("Xianggelila");
        locations.add("Xizang"); locations.add("Gulangyu");
        locations.add("Zhangjiajie"); locations.add("Jiuzhaigou");
        locations.add("Japan"); locations.add("Korean");
        locations.add("Jeju"); locations.add("Bali");
        locations.add("Phuket"); locations.add("Boracay");
        locations.add("Saipan"); locations.add("Singapore");
        locations.add("Malaysia"); locations.add("Thailand");
        locations.add("Philippines"); locations.add("Indonesia");
        locations.add("India"); locations.add("Nepal");
        locations.add("Dubai"); locations.add("Turkey");
        locations.add("Greek"); locations.add("US");
        locations.add("Canada"); locations.add("Australia");
        locations.add("Britain"); locations.add("France");
        locations.add("Italy"); locations.add("Spain");
        locations.add("Portugal"); locations.add("Netherlands");
        locations.add("Belgium"); locations.add("Swiss");
        locations.add("Swedish"); locations.add("Danish");
        locations.add("Finland"); locations.add("Czech");
        locations.add("Cuba"); locations.add("Argentina");
        locations.add("Brazil"); locations.add("Russia");
        locations.add("England"); locations.add("Ireland");
        locations.add("Germany");

        List<String> films = new LinkedList<>();
        films.add("The Shawshank Redemption"); films.add("Forrest Gump");
        films.add("Inception"); films.add("The Matrix");
        films.add("The Legend Of 1900"); films.add("Spirited Away");
        films.add("Roman Holiday"); films.add("Schindler's List");
        films.add("Amelie from Montmartre"); films.add("A Beautiful Mind");
        films.add("Farewell My concubine"); films.add("Leon");
        films.add("The Godfather"); films.add("Titanic");
        films.add("Batman"); films.add("Pulp Fiction");
        films.add("Fight Club"); films.add("The Upside");
        films.add("Hachiko: A Dog's Story"); films.add("Gone with the Wind");
        films.add("The Pursuit of Happiness"); films.add("One Flew Over the Cuckoo's Nest");
        films.add("Scent Of A Woman"); films.add("\"Dead Poets Society");
        films.add("The Lord of the Rings"); films.add("Harry Potter");
        films.add("Infernal Affairs"); films.add("American Dreams in China");
        films.add("Chungking express"); films.add("the silence of the lambs");

        List<String> books = new LinkedList<>();
        books.add("A Tale of Two Cities"); books.add("Anna Karenina");
        books.add("Crime and Punishment"); books.add("David Copperfield");
        books.add("Emma"); books.add("For Whom the Bell Tolls");
        books.add("Gone with the Wind"); books.add("Great Expectations");
        books.add("Hamlet"); books.add("Jane Eyre");
        books.add("Jean Christophe"); books.add("King Lear");
        books.add("Les Miserables"); books.add("Little Women");
        books.add("Oliver Twist"); books.add("Pride and Prejudice");
        books.add("Resurrection"); books.add("Robinson Crusoe");
        books.add("Sense and Sensibility"); books.add("The Great Gatsby");
        books.add("The Old Man and the Sea"); books.add("Walden");

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

        List<String> activities = new LinkedList<>();
        activities.add("blind date");
        activities.add("picnic");
        activities.add("home party");
        activities.add("barbeque");
        activities.add("role playing detective");
        activities.add("KTV");
        activities.add("hiking");
        activities.add("others");

        List<List<String>> lists = new LinkedList<>();
        lists.add(locations);
        lists.add(works);
        lists.add(food);
        lists.add(films);
        lists.add(books);
        lists.add(sports);
        lists.add(activities);

        int maxLength = lists.get(0).size();
        for (List<String> eachList: lists) {
            if (maxLength < eachList.size()) maxLength = eachList.size();
        }

        List<Iterator<String>> iterators = new LinkedList<>();
        for (List<String> eachList: lists) {
            iterators.add(eachList.iterator());
        }

        for (int i = 0; i < maxLength; i++) {
            List<String> eachRow = new LinkedList<>();
            for (Iterator<String> eachIterator: iterators) {
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

    public static void main(String[] args) {
        initialize();
    }
}
