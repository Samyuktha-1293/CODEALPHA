import java.util.Scanner;

public class HotelReviewApp {
    
    private static User[] users = new User[100];
    private static Hotel[] hotels = new Hotel[100];
    private static Review[] reviews = new Review[100];
    private static int userCount = 0;
    private static int hotelCount = 0;
    private static int reviewCount = 0;
    private static int currentUserId = -1; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Add Hotel");
            System.out.println("4. Add Review");
            System.out.println("5. View Reviews by Hotel");
            System.out.println("6. View Reviews by Rating");
            System.out.println("7. Exit");
            System.out.print("Select the Operation (1-7): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    addHotel(scanner);
                    break;
                case 4:
                    addReview(scanner);
                    break;
                case 5:
                    viewReviewsByHotel(scanner);
                    break;
                case 6:
                    viewReviewsByRating(scanner);
                    break;
                case 7:
                	System.out.println("You choose to Exit..");
                	System.out.println("Thankyou for reaching us..! visit again...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = new User(userCount + 1, username, password);
        users[userCount++] = user;
        System.out.println("Registration successful.");
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        for (int i = 0; i < userCount; i++) {
            User user = users[i];
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUserId = user.getId();
                System.out.println("Login successful.");
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    private static void addHotel(Scanner scanner) {
        if (currentUserId == -1) {
            System.out.println("Please log in first.");
            return;
        }
        System.out.print("Enter hotel name: ");
        String name = scanner.nextLine();
        System.out.print("Enter hotel location: ");
        String location = scanner.nextLine();
        Hotel hotel = new Hotel(hotelCount + 1, name, location);
        hotels[hotelCount++] = hotel;
        System.out.println("Hotel added successfully.");
    }

    private static void addReview(Scanner scanner) {
        if (currentUserId == -1) {
            System.out.println("Please log in first.");
            return;
        }
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter comment: ");
        String comment = scanner.nextLine();
        boolean hotelExists = false;
        for (int i = 0; i < hotelCount; i++) {
            if (hotels[i].getId() == hotelId) {
                hotelExists = true;
                break;
            }
        }
        if (!hotelExists) {
            System.out.println("Hotel not found.");
            return;
        }
        Review review = new Review(reviewCount + 1, hotelId, currentUserId, rating, comment);
        reviews[reviewCount++] = review;
        System.out.println("Review added successfully.");
    }

    private static void viewReviewsByHotel(Scanner scanner) {
        System.out.print("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        scanner.nextLine(); 
        boolean found = false;
        for (int i = 0; i < reviewCount; i++) {
            Review review = reviews[i];
            if (review.getHotelId() == hotelId) {
                System.out.println(review);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No reviews for this hotel.");
        }
    }

    private static void viewReviewsByRating(Scanner scanner) {
        System.out.print("Enter minimum rating (1-5): ");
        int minRating = scanner.nextInt();
        scanner.nextLine(); 
        boolean found = false;
        for (int i = 0; i < reviewCount; i++) {
            Review review = reviews[i];
            if (review.getRating() >= minRating) {
                System.out.println(review);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No reviews with this rating.");
        }
    }

    static class User {
        private final int id;
        private final String username;
        private final String password;

        public User(int id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
        }

        public int getId() {
        	return id; 
        }
        
        public String getUsername() {
        	return username; 
        }
        
        public String getPassword() {
        	return password; 
        }
        

        public String toString() {
            return "User{id=" + id + ", username='" + username + "'}";
        }
    }

    static class Hotel {
        private final int id;
        private final String name;
        private final String location;

        public Hotel(int id, String name, String location) {
            this.id = id;
            this.name = name;
            this.location = location;
        }

        public int getId() {
        	return id; 
        }
        
        public String getName() {
        	return name; 
        }
        
        public String getLocation() {
        	return location; 
        }
        

        public String toString() {
            return "Hotel{id=" + id + ", name='" + name + "', location='" + location + "'}";
        }
    }

    static class Review {
        private final int id;
        private final int hotelId;
        private final int userId;
        private final int rating;
        private final String comment;

        public Review(int id, int hotelId, int userId, int rating, String comment) {
            this.id = id;
            this.hotelId = hotelId;
            this.userId = userId;
            this.rating = rating;
            this.comment = comment;
        }

        public int getId() { 
        	return id; 
        }
        
        public int getHotelId() {
        	return hotelId; 
        }
        
        public int getUserId() {
        	return userId; 
        }
        
        public int getRating() {
        	return rating; 
        }
        
        public String getComment() {
        	return comment; 
        }

        public String toString() {
            return "Review{id=" + id + ", hotelId=" + hotelId + ", userId=" + userId +
                   ", rating=" + rating + ", comment='" + comment + "'}";
        }
    }
}
