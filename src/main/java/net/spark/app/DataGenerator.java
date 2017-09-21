package net.spark.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import net.jiniguru.roshan.backend.CustomerRepository;
import net.jiniguru.roshan.backend.OrderRepository;
import net.jiniguru.roshan.backend.PickupLocationRepository;
import net.jiniguru.roshan.backend.ProductRepository;
import net.jiniguru.roshan.backend.ProductWineRepository;
import net.jiniguru.roshan.backend.UserRepository;
import net.jiniguru.roshan.backend.data.OrderState;
import net.jiniguru.roshan.backend.data.Role;
import net.jiniguru.roshan.backend.data.entity.AdditionalDetail;
import net.jiniguru.roshan.backend.data.entity.Children;
import net.jiniguru.roshan.backend.data.entity.Customer;
import net.jiniguru.roshan.backend.data.entity.HistoryItem;
import net.jiniguru.roshan.backend.data.entity.Order;
import net.jiniguru.roshan.backend.data.entity.OrderItem;
import net.jiniguru.roshan.backend.data.entity.PickupLocation;
import net.jiniguru.roshan.backend.data.entity.Product;
import net.jiniguru.roshan.backend.data.entity.ProductWine;
import net.jiniguru.roshan.backend.data.entity.User;
import net.jiniguru.roshan.backend.util.WineSheetTemplate;

@SpringComponent
public class DataGenerator implements HasLogger {

	private static final String[] FILLING = new String[] { 
			"Roche Mazet",
			"Les Ormes de Cambas",
			"Vieux Papes",
			"La Villageoise	",
			"Cellier des Dauphins",
			"J.P. Chenet",
			"Cambras",
			"Listel	Vranken-Pommery",
			"Baron de Lestac",
			"Plessis Duval",
			"Cramoisay",
			"Lichette",
			"les Combes de Saint-Sauveur",
			"Billette",
			"Couleurs du Sud",
			"Montalcour",
			"Champlure",
			"Blaissac",
			"Roche-Linières",
			"Grand Sud" };
	private static final String[] TYPE = new String[] {"ajaccio",
			"aloxe-corton",
			"alsace ou vin d'Alsace",
			"alsace grand cru",
			"anjou",
			"anjou-coteaux-de-la-loire",
			"anjou villages",
			"anjou villages brissac",
			"arbois",
			"auxey-duresses",
			"bandol",
			"banyuls",
			"banyuls grand cru",
			"barsac",
			"bâtard-montrachet",
			"béarn",
			"beaujolais",
			"beaujolais-villages",
			"beaumes-de-venise",
			"beaune",
			"bellet ou vin de Bellet",
			"bergerac",
			"bienvenues-bâtard-montrachet",
			"blagny",
			"blaye",
			"bonnes-mares",
			"bonnezeaux",
			"bordeaux",
			"bordeaux supérieur",
			"bourgogne",
			"bourgogne aligoté",
			"coteaux-bourguignons",
			"Boudes",
			"bourgogne mousseux",
			"bourgogne passe-tout-grains",
			"bourgueil",
			"bouzeron",
			"brouilly",
			"bugey",
			"buzet",
			"cabardès",
			"cabernet d'Anjou",
			"cabernet de Saumur",
			"cadillac",
			"cahors",
			"canon-fronsac",
			"cassis",
			"cérons",
			"chablis",
			"chablis grand cru",
			"chambertin",
			"chambertin-clos-de-bèze",
			"chambolle-musigny",
			"champagne",
			"Chanturgue",
			"chapelle-chambertin",
			"charlemagne",
			"charmes-chambertin",
			"chassagne-montrachet",
			"château-chalon",
			"Châteaugay",
			"château-grillet",
			"châteaumeillant",
			"châteauneuf-du-pape",
			"châtillon-en-diois",
			"chénas",
			"chevalier-montrachet",
			"cheverny",
			"chinon",
			"chiroubles",
			"chorey-lès-beaune",
			"clairette de Bellegarde",
			"clairette de Die",
			"clairette du Languedoc",
			"clos-de-la-roche",
			"clos-de-tart",
			"clos-de-vougeot ou clos-vougeot",
			"clos-des-lambrays",
			"clos-saint-denis",
			"collioure",
			"condrieu",
			"corbières",
			"corbières-boutenac",
			"Corent",
			"cornas",
			"corse ou vin de Corse",
			"corton",
			"corton-charlemagne",
			"costières-de-nîmes",
			"côte-de-beaune",
			"côte-de-beaune-villages",
			"côte-de-nuits-villages",
			"côte-de-brouilly",
			"côte-roannaise",
			"côte-rôtie",
			"coteaux-champenois",
			"coteaux-d'aix-en-provence",
			"coteaux-de-die",
			"coteaux-de-l'aubance",
			"coteaux-de-saumur",
			"coteaux-du-giennois",
			"coteaux-du-layon",
			"coteaux-du-loir",
			"coteaux-du-lyonnais",
			"coteaux-du-vendômois",
			"coteaux-varois-en-provence",
			"côtes-de-bergerac",
			"côtes-de-blaye",
			"côtes-de-bordeaux",
			"côtes-de-bordeaux-saint-macaire",
			"côtes-de-bourg ou bourg ou bourgeais",
			"côtes-de-duras",
			"côtes-de-montravel",
			"côtes-de-provence",
			"côtes-de-toul",
			"côtes-du-forez",
			"côtes-du-jura",
			"côtes-du-marmandais",
			"côtes-du-rhône",
			"Côtes-du-rhône villages",
			"côtes-du-roussillon",
			"Côtes-du-roussillon villages",
			"côtes-du-vivarais",
			"cour-cheverny",
			"crémant d'Alsace",
			"crémant de Bordeaux",
			"crémant de Bourgogne",
			"crémant de Die",
			"crémant de Limoux",
			"crémant de Loire",
			"crémant du Jura",
			"criots-bâtard-montrachet",
			"crozes-hermitage ou crozes-ermitage",
			"duché-d'Uzès",
			"échezeaux",
			"entre-deux-mers",
			"faugères",
			"fiefs-vendéens",
			"fitou",
			"fixin",
			"fleurie",
			"fronsac",
			"fronton",
			"gaillac",
			"gaillac-premières-côtes",
			"gevrey-chambertin",
			"gigondas",
			"givry",
			"grands-échezeaux",
			"graves",
			"graves-de-vayres",
			"graves-supérieures",
			"grignan-les-adhémar",
			"griotte-chambertin",
			"haut-médoc",
			"haut-montravel",
			"haut-poitou",
			"hermitage ou ermitage",
			"irancy",
			"irouléguy",
			"jasnières",
			"juliénas",
			"jurançon",
			"l'étoile",
			"la-grande-rue",
			"la-romanée",
			"la-tâche",
			"ladoix",
			"lalande-de-pomerol",
			"languedoc",
			"latricières-chambertin",
			"les-baux-de-provence",
			"limoux",
			"lirac",
			"listrac-médoc",
			"loupiac",
			"luberon",
			"lussac-saint-émilion"
			};
	private static final String[] FIRST_NAME0 = new String[] { "Ori", "Amanda", "Octavia", "Laurel", "Lael", "Delilah",
			"Jean", "Skyler", "Arsenio", "Haley", "Lionel", "Sylvia", "Jessica", "Lester", "Ferdinand", "Elaine",
			"Griffin", "Kerry", "Dominique" };
	private static final String[] FIRST_NAME = new String[] { "Martin",
			"Bernard",
			"Dubois",
			"Thomas",
			"Robert",
			"Richard",
			"Petit",
			"Durand",
			"Leroy",
			"Moreau",
			"Simon",
			"Laurent",
			"Lefebvre",
			"Michel",
			"Garcia",
			"David",
			"Bertrand",
			"Roux",
			"Vincent",};
	private static final String[] LAST_NAME = new String[] { "Fournier",
			"Morel",
			"Girard",
			"Andre",
			"Lefevre",
			"Mercier",
			"Dupont",
			"Lambert",
			"Bonnet",
			"Francois",
			"Martinez",
			"Legrand",
			"Garnier",
			"Faure",
			"Rousseau",
			"Blanc",
			"Guerin",
			"Muller",
			"Henry",
			"Roussel",
			"Nicolas",
			"Perrin",
			"Morin",
			"Mathieu",
			"Clement",
			"Gauthier",
			"Dumont",
			"Lopez",
			"Fontaine",
			"Chevalier",
			"Martin",
			"Bernard",
			"Dubois",
			"Thomas"
			};

	private final PasswordEncoder passwordEncoder;

	private final Random random = new Random(1L);

	private final List<PickupLocation> pickupLocations = new ArrayList<>();
	private final List<Product> products = new ArrayList<>();
	private final List<Customer> customers = new ArrayList<>();
	private final List<User> users = new ArrayList<>();
	private final List<Order> orders = new ArrayList<>();
	private User baker;
	private User barista;
	private User admin;

	@Autowired
	public DataGenerator(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public CommandLineRunner loadData(OrderRepository orders, UserRepository users, ProductRepository products,
			CustomerRepository customers, PickupLocationRepository pickupLocations, PasswordEncoder passwordEncoder,
			ProductWineRepository proWineRepo) {
		return args -> {
			if (users.count() != 0L) {
				getLogger().info("Using existing database");
				return;
			}

			getLogger().info("Generating demo data");
			getLogger().info("... generating users");
			createUsers(users);
			getLogger().info("... generating wine catalogue");
			createWineCatalog(proWineRepo);
			getLogger().info("... generating products");
			createProducts(products,proWineRepo);
			getLogger().info("... generating customers");
			createCustomers(customers);
			getLogger().info("... generating pickup locations");
			createPickupLocations(pickupLocations);
			getLogger().info("... generating orders");
			createOrders(orders);

			getLogger().info("Generated demo data");
		};
	}



	private void createWineCatalog(ProductWineRepository proWineRepo) {
		// TODO Auto-generated method stub
		
	}

	private void createCustomers(CustomerRepository customerRepo) {
		for (int i = 0; i < 100; i++) {
			customers.add(createCustomer(customerRepo));
		}
	}

	private Customer createCustomer(CustomerRepository customerRepo) {
		Customer customer = new Customer();
		String first = getRandom(FIRST_NAME);
		String last = getRandom(LAST_NAME);
		customer.setLastName(last);
		customer.setPhoneNumber(getRandomPhone());
		customer.setEmail(first+"@gmail.com");
		customer.setDob(LocalDate.now());
		customer.setDoe(LocalDate.now());
		customer.setTom("Option1");
		customer.setMemSince(LocalDate.now());
		customer.setCompany("Aqui");
		customer.setBillAddress("Ile Maurice");
		customer.setDeliveryAddress("Ile Maurice");
		customer.setHomeNumber("+230 7123458");
		customer.setMobileNumber("+230 7123458");
		customer.setQrCode("+230 7123458");
		customer.setCoursedFollowed("+230 7123458");
		customer.setNote("N/A");
		customer.setStatus("active");
		List<Children> childrenList = new ArrayList<>();
		childrenList.add(new Children("Jean",LocalDate.now()));
		customer.setChildrenList(childrenList);
		customer.setFirstName(first);
		if (random.nextInt(10) == 0) {
			customer.setDetails("Very important customer");
		}
		System.out.println(customer);
		return customerRepo.save(customer);
	}

	private String getRandomPhone() {
		return "+230-5-" + String.format("%07d", random.nextInt(10000));
	}

	private void createOrders(OrderRepository orderRepo) {
		int yearsToInclude = 2;
		LocalDate now = LocalDate.now();
		LocalDate oldestDate = LocalDate.of(now.getYear() - yearsToInclude, 1, 1);
		LocalDate newestDate = now.plusMonths(1L);

		for (LocalDate dueDate = oldestDate; dueDate.isBefore(newestDate); dueDate = dueDate.plusDays(1)) {
			// Create a slightly upwards trend - everybody wants to be
			// successful
			int relativeYear = dueDate.getYear() - now.getYear() + yearsToInclude;
			int relativeMonth = relativeYear * 1 + dueDate.getMonthValue(); // multiplier for month set to 1
			double multiplier = 1.0 + 0.03 * relativeMonth;
			int ordersThisDay = (int) (random.nextInt(10) + 1 * multiplier);
			for (int i = 0; i < ordersThisDay; i++) {
				orders.add(createOrder(dueDate));
			}
		}
		orderRepo.save(orders);
	}

	private Order createOrder(LocalDate dueDate) {
		Order order = new Order();

		order.setCustomer(getRandomCustomer());
		order.setPickupLocation(getRandomPickupLocation());
		order.setDueDate(dueDate);
		order.setDueTime(getRandomDueTime());
		order.setState(getRandomState(order.getDueDate()));

		int itemCount = random.nextInt(3);
		List<OrderItem> items = new ArrayList<>();
		for (int i = 0; i <= itemCount; i++) {
			OrderItem item = new OrderItem();
			Product product;
			do {
				product = getRandomProduct();
			} while (containsProduct(items, product));
			item.setProduct(product);
			item.setQuantity(random.nextInt(10) + 1);
			if (random.nextInt(5) == 0) {
				if (random.nextBoolean()) {
					item.setComment("Collection");
				} else {
					item.setComment("Common");
				}
			}
			items.add(item);
		}
		order.setItems(items);

		order.setHistory(createOrderHistory(order));

		return order;
	}

	private List<HistoryItem> createOrderHistory(Order order) {
		ArrayList<HistoryItem> history = new ArrayList<>();
		HistoryItem item = new HistoryItem(getBarista(), "Order placed");
		item.setNewState(OrderState.NEW);
		LocalDateTime orderPlaced = order.getDueDate().minusDays(random.nextInt(5) + 2L).atTime(random.nextInt(10) + 7,
				00);
		item.setTimestamp(orderPlaced);
		history.add(item);
		if (order.getState() == OrderState.CANCELLED) {
			item = new HistoryItem(getBarista(), "Order cancelled");
			item.setNewState(OrderState.CANCELLED);
			item.setTimestamp(orderPlaced.plusDays(random
					.nextInt((int) orderPlaced.until(order.getDueDate().atTime(order.getDueTime()), ChronoUnit.DAYS))));
			history.add(item);
		} else if (order.getState() == OrderState.CONFIRMED || order.getState() == OrderState.DELIVERED
				|| order.getState() == OrderState.PROBLEM || order.getState() == OrderState.READY) {
			item = new HistoryItem(getBaker(), "Order confirmed");
			item.setNewState(OrderState.CONFIRMED);
			item.setTimestamp(orderPlaced.plusDays(random.nextInt(2)).plusHours(random.nextInt(5)));
			history.add(item);

			if (order.getState() == OrderState.PROBLEM) {
				item = new HistoryItem(getBaker(), "Can't make it. Did not get any ingredients this morning");
				item.setNewState(OrderState.PROBLEM);
				item.setTimestamp(order.getDueDate().atTime(random.nextInt(4) + 4, 0));
				history.add(item);
			} else if (order.getState() == OrderState.READY || order.getState() == OrderState.DELIVERED) {
				item = new HistoryItem(getBaker(), "Order ready for pickup");
				item.setNewState(OrderState.READY);
				item.setTimestamp(order.getDueDate().atTime(random.nextInt(2) + 8, random.nextBoolean() ? 0 : 30));
				history.add(item);
				if (order.getState() == OrderState.DELIVERED) {
					item = new HistoryItem(getBaker(), "Order delivered");
					item.setNewState(OrderState.DELIVERED);
					item.setTimestamp(order.getDueDate().atTime(order.getDueTime().minusMinutes(random.nextInt(120))));
					history.add(item);
				}
			}
		}

		return history;
	}

	private boolean containsProduct(List<OrderItem> items, Product product) {
		for (OrderItem item : items) {
			if (item.getProduct() == product) {
				return true;
			}
		}
		return false;
	}

	private LocalTime getRandomDueTime() {
		int time = 8 + 4 * random.nextInt(3);

		return LocalTime.of(time, 0);
	}

	private OrderState getRandomState(LocalDate due) {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		LocalDate twoDays = today.plusDays(2);

		if (due.isBefore(today)) {
			if (random.nextDouble() < 0.9) {
				return OrderState.DELIVERED;
			} else {
				return OrderState.CANCELLED;
			}
		} else {
			if (due.isAfter(twoDays)) {
				return OrderState.NEW;
			} else if (due.isAfter(tomorrow)) {
				// in 1-2 days
				double resolution = random.nextDouble();
				if (resolution < 0.8) {
					return OrderState.NEW;
				} else if (resolution < 0.9) {
					return OrderState.PROBLEM;
				} else {
					return OrderState.CANCELLED;
				}
			} else {
				double resolution = random.nextDouble();
				if (resolution < 0.6) {
					return OrderState.READY;
				} else if (resolution < 0.8) {
					return OrderState.DELIVERED;
				} else if (resolution < 0.9) {
					return OrderState.PROBLEM;
				} else {
					return OrderState.CANCELLED;
				}
			}

		}
	}

	private Product getRandomProduct() {
		double cutoff = 2.5;
		double g = random.nextGaussian();
		g = Math.min(cutoff, g);
		g = Math.max(-cutoff, g);
		g += cutoff;
		g /= (cutoff * 2.0);

		return products.get((int) (g * (products.size() - 1)));
	}

	private PickupLocation getRandomPickupLocation() {
		return getRandom(pickupLocations);
	}

	private Customer getRandomCustomer() {
		return getRandom(customers);
	}

	private User getBaker() {
		return baker;
	}

	private User getBarista() {
		return barista;
	}

	private <T> T getRandom(List<T> items) {
		return items.get(random.nextInt(items.size()));
	}

	private <T> T getRandom(T[] array) {
		return array[random.nextInt(array.length)];
	}

	private void createPickupLocations(PickupLocationRepository pickupRepo) {
		PickupLocation store = new PickupLocation();
		store.setName("Store");
		pickupLocations.add(pickupRepo.save(store));
		PickupLocation bakery = new PickupLocation();
		bakery.setName("Online");
		pickupLocations.add(pickupRepo.save(bakery));
	}

	private void createProducts(ProductRepository productsRepo, ProductWineRepository proWineRepo) {
		for (int i = 0; i < 10; i++) {
			Product product = new Product();
			List<AdditionalDetail> productDetails = new ArrayList<>();
			int j = 0 ;
			for (String field : WineSheetTemplate.WINE) {
				AdditionalDetail e = new AdditionalDetail();
				e.setName(field);
				e.setValue(WineSheetTemplate.WINE_VALUE[j]);
				productDetails.add(e);
				j++;
			}
			
			product.setProductDetails(productDetails);
			product.setName(getRandomProductName());
			double doublePrice = 2.0 + random.nextDouble() * 100.0;
			product.setPrice((int) (doublePrice * 100.0));
			ProductWine pw = new ProductWine();
			pw.setTypeOfWine("pinotage");
			proWineRepo.save(pw);
			product.setProductWine(pw);
			
			products.add(productsRepo.save(product));
		}
	}

	private String getRandomProductName() {
		String firstFilling = getRandom(FILLING);
		String name;
		if (random.nextBoolean()) {
			String secondFilling;
			do {
				secondFilling = getRandom(FILLING);
			} while (secondFilling.equals(firstFilling));

			name = firstFilling + " " + secondFilling;
		} else {
			name = firstFilling;
		}
		name += " " + getRandom(TYPE);

		return name;
	}

	private void createUsers(UserRepository userRepository) {
		baker = userRepository.save(new User("vendeuse@aqui.com", "Heidi", passwordEncoder.encode("vin"), Role.BAKER));
		User user = new User("barista@aqui.com", "Malin", passwordEncoder.encode("vin"), Role.BARISTA);
		user.setLocked(true);
		barista = userRepository.save(user);
		user = new User("d@a.com", "Delphine", passwordEncoder.encode("vin"), Role.ADMIN);
		user.setLocked(true);
		admin = userRepository.save(user);
		users.add(barista);
		users.add(admin);
		users.add(baker);
	}
}
