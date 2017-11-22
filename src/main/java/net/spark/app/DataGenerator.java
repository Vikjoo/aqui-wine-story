package net.spark.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import net.spark.backend.CurrencyRepository;
import net.spark.backend.CustomerRepository;
import net.spark.backend.OrderRepository;
import net.spark.backend.PaymentTermRepository;
import net.spark.backend.PickupLocationRepository;
import net.spark.backend.PricingStrategyRepository;
import net.spark.backend.ProductRepository;
import net.spark.backend.ProductWineRepository;
import net.spark.backend.UserRepository;
import net.spark.backend.WineRepository;
import net.spark.backend.data.OrderState;
import net.spark.backend.data.PricingType;
import net.spark.backend.data.Role;
import net.spark.backend.data.entity.AdditionalDetail;
import net.spark.backend.data.entity.Address;
import net.spark.backend.data.entity.Children;
import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.Customer;
import net.spark.backend.data.entity.HistoryItem;
import net.spark.backend.data.entity.Order;
import net.spark.backend.data.entity.OrderItem;
import net.spark.backend.data.entity.PaymentTerm;
import net.spark.backend.data.entity.PickupLocation;
import net.spark.backend.data.entity.PricingStrategy;
import net.spark.backend.data.entity.Product;
import net.spark.backend.data.entity.ProductWine;
import net.spark.backend.data.entity.Stock;
import net.spark.backend.data.entity.User;
import net.spark.backend.util.WineSheetTemplate;
import net.spark.cellar.Name;
import net.spark.cellar.TypeOfWine;
import net.spark.cellar.Wine;

@SpringComponent
public class DataGenerator implements HasLogger {

	private static final String[] FILLING = new String[] { "Roche Mazet", "Les Ormes de Cambas", "Vieux Papes",
			"La Villageoise	", "Cellier des Dauphins", "J.P. Chenet", "Cambras", };
	private static final String[] TYPE = new String[] { "gaillac-premières-côtes", "gevrey-chambertin", "gigondas",
			"givry", "grands-échezeaux", "graves", "graves-de-vayres", "graves-supérieures", "grignan-les-adhémar",
			"griotte-chambertin", "haut-médoc", "haut-montravel", "haut-poitou", "hermitage ou ermitage", "irancy",
			"irouléguy",

	};
	private static final String[] FIRST_NAME0 = new String[] { "Ori", "Amanda", "Octavia", "Laurel", "Lael", "Delilah",
			"Jean", "Skyler", "Arsenio", "Haley", "Lionel", "Sylvia", "Jessica", "Lester", "Ferdinand", "Elaine",
			"Griffin", "Kerry", "Dominique" };
	private static final String[] FIRST_NAME = new String[] { "Martin", "Bernard", "Dubois", "Thomas", "Robert",
			"Richard", "Petit", "Durand", };
	private static final String[] LAST_NAME = new String[] { "Fournier", "Morel", "Girard", "Andre", "Lefevre",
			"Mercier", "Dupont", "Lambert", "Bonnet", "Francois", "Martinez", "Legrand", "Garnier", "Faure" };

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
			ProductWineRepository proWineRepo, WineRepository wineRepository,CurrencyRepository currencyRepository,
			PricingStrategyRepository pricingStrategyRepository, PaymentTermRepository paymentTermRepo) {
		return args -> {
			if (users.count() != 0L) {
				getLogger().info("Using existing database");
				return;
			}

			getLogger().info("Generating demo data");
			getLogger().info("... generating currencies");
			createCurrencies(currencyRepository);
			getLogger().info("... generating pricing strategies");
			createPricingStrategies(pricingStrategyRepository);
			
			getLogger().info("... generating payment Terms");
			createPaymentTerms(paymentTermRepo);
			getLogger().info("... generating users");
			createUsers(users);
			getLogger().info("... generating wine catalogue");
			createWineCatalog(proWineRepo);
			getLogger().info("... generating products");
			createProducts(products, proWineRepo);
			getLogger().info("... generating customers");
			createCustomers(customers);
			getLogger().info("... generating pickup locations");
			createPickupLocations(pickupLocations);
			getLogger().info("... generating orders");
			createOrders(orders);
			createWine(wineRepository);
			getLogger().info("Generated demo data");
		};
	}

	private void createPaymentTerms(PaymentTermRepository paymentTermRepo) {
		PaymentTerm paymentTerm = new  PaymentTerm();
		paymentTerm.setName("Cash on Delivery");
		paymentTermRepo.save(paymentTerm);
		PaymentTerm paymentTerm2 = new  PaymentTerm();
		paymentTerm2.setName("30% down payment, rest on delivery");
		paymentTermRepo.save(paymentTerm2);
		PaymentTerm paymentTerm3 = new  PaymentTerm();
		paymentTerm3.setName("30% down payment, rest within set period");
		paymentTermRepo.save(paymentTerm3);
		
		PaymentTerm paymentTerm4 = new  PaymentTerm();
		paymentTerm4.setName("50% down Payment within period, rest on delivery");
		paymentTermRepo.save(paymentTerm4);
		
		PaymentTerm paymentTerm5 = new  PaymentTerm();
		paymentTerm5.setName("50% down Payment within period, rest within set period");
		paymentTermRepo.save(paymentTerm5);
		PaymentTerm paymentTerm6 = new  PaymentTerm();
		paymentTerm6.setName("Full payment in set period");
		paymentTermRepo.save(paymentTerm6);
		
	}

	private void createPricingStrategies(PricingStrategyRepository pricingStrategyRepository) {
		PricingStrategy entity = new PricingStrategy();
		 entity.setName("Public VAT Enable");
		 entity.setPricingType(PricingType.VAT_ENABLE);
		pricingStrategyRepository.save(entity);
		PricingStrategy entity2 = new PricingStrategy();
		 entity2.setName("Whole Sale B2B");
		 entity2.setPricingType(PricingType.WHOLESALE);
		pricingStrategyRepository.save(entity2);
		PricingStrategy entity3 = new PricingStrategy();
		 entity3.setName("Discount @ Wholesale");
		 entity3.setPricingType(PricingType.DISCOUNT_WHOLESALE);
		pricingStrategyRepository.save(entity3);
	}

	private void createCurrencies(CurrencyRepository currencyRepository) {
		Currency currency = new Currency();
		currency.setName("MUR");
		currency.setRate(1D);
		Currency currency2 = new Currency();
		currency2.setName("EUR");
		currency2.setRate(40.01D);
		Currency currency3 = new Currency();
		currency3.setName("USD");
		currency3.setRate(34.30D);
		
		currencyRepository.save(currency);
		currencyRepository.save(currency2);
		currencyRepository.save(currency3);
	}

	private void createWineCatalog(ProductWineRepository proWineRepo) {
	

	}

	private void createCustomers(CustomerRepository customerRepo) {
		for (int i = 0; i < 10; i++) {
			customers.add(createCustomer(customerRepo));
		}
	}

	private Customer createCustomer(CustomerRepository customerRepo) {
		Customer customer = new Customer();
		String first = getRandom(FIRST_NAME);
		String last = getRandom(LAST_NAME);
		customer.setLastName(last);
		customer.setName(first +" "+ last);
		customer.setPhoneNumber(getRandomPhone());
		customer.setEmail(first + "@gmail.com");
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
		childrenList.add(new Children("Jean", LocalDate.now()));
		customer.setChildrenList(childrenList);
		customer.setFirstName(first);
		if (random.nextInt(10) == 0) {
			customer.setDetails("Very important customer");
		}
		Address address = new Address();
		address.setCity("Ebene");
		address.setStreet("11 Trotter Street");
		address.setState("Riviere du Rampart");
		address.setZip("B3K 2C7");
		customer.setAddress(address);
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

	private void createProductsAuto(ProductRepository productsRepo, ProductWineRepository proWineRepo) {
		for (int i = 0; i < 10; i++) {
			Product product = new Product();
			List<AdditionalDetail> productDetails = new ArrayList<>();
			int j = 0;
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

	private void createProductsPoi(ProductRepository productsRepo, ProductWineRepository proWineRepo) {
		//
		// Reference
		// Pays
		// Région
		// Domaine
		// Cuvée
		// Couleur
		// Appellation
		// Millesime
		// P.U H.T
		// Cost Price
		// Stock
		// Wholesale Price H.T.
		// Wholesale Price T.T.C
		// Margin Wholesale
		// Public Price H.T.
		// Public Price T.T.C
		// Margin Public Price
		String FILE_NAME = "/Users/rgunoo/userdev/tmp/histoire-de-win-pos/Liste Roshan.xlsx";
		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			String[] fieldNames = new String[] { "Reference", "Pays", "Région", "Domaine	", "Cuvée", "Couleur",
					"Appellation", "Millesime", "P.U H.T", "Cost Price", "Stock", "Wholesale Price H.T.",
					"Wholesale Price T.T.C", "Margin Wholesale", "Public Price H.T.", "Public Price T.T.C",
					"Margin Public Price" };
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int row = 54;
			iterator.next(); // skip first row
			while (iterator.hasNext() && row >= 0) {
				System.out.print(row + "#");

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.cellIterator();// iterator();
				Product product = new Product();
				int i = 0;
				List<AdditionalDetail> productDetails = new ArrayList<>();
				StringBuilder concatenatedName = new StringBuilder();
				int costPrice = 0;
				String color = "";
				while (cellIterator.hasNext() && i < fieldNames.length) {
					// System.out.println("I="+i);
					Cell currentCell = cellIterator.next();
					// getCellTypeEnum shown as deprecated for version 3.15
					// getCellTypeEnum ill be renamed to getCellType starting from version 4.0
					String genValue = "";
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						genValue = currentCell.getStringCellValue();
						System.out.print("[" + row + "," + i + "]" + currentCell.getStringCellValue() + "--");
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						System.out.print("[" + row + "," + i + "]" + currentCell.getNumericCellValue() + "--");
						genValue = "" + currentCell.getNumericCellValue();
					} else if (currentCell.getCellTypeEnum() == CellType.BLANK) {
						genValue = "BLANK";
					}
					if (i == 6 || i == 7)
						concatenatedName.append(genValue);
					if (i == 6)
						concatenatedName.append('-');
					if (i == 9)
						costPrice = (int) currentCell.getNumericCellValue();
					if (i == 5)
						color = currentCell.getStringCellValue();
					AdditionalDetail e = new AdditionalDetail();
					e.setName(fieldNames[i]);
					e.setValue(genValue);
					productDetails.add(e);
					i++;
				}

				product.setProductDetails(productDetails);
				product.setName(concatenatedName.toString());

				product.setPrice(costPrice * 100);
				ProductWine pw = new ProductWine();
				pw.setTypeOfWine(color);
				proWineRepo.save(pw);
				product.setProductWine(pw);

				products.add(productsRepo.save(product));
				System.out.println();

				row--;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	static final String[] FIELD_NAMES = new String[] { "Reference", // 0
			"Pays", // 1
			"Région", // 2
			"Domaine", // 3
			"Cuvée", // 4
			"Couleur", "Appellation", // 6
			"Millesime", // 7
			"P.U H.T", // 8
			"Cost Price", // 9
			"Stock", // 10
			"Wholesale Price H.T.", // 11
			"Wholesale Price T.T.C", // 12
			"Margin Wholesale", // 13
			"Public Price H.T.", // 14
			"Public Price T.T.C", // 15
			"Margin Public Price" // 16
	};
	String FILE_NAME = "/Users/rgunoo/userdev/tmp/histoire-de-win-pos/Liste Roshan.xlsx";

	private void createProducts(ProductRepository productsRepo, ProductWineRepository proWineRepo) {

		try {
			;
			InputStream excelFile = new ClassPathResource("products.xlsx").getInputStream();

			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(0);
			// Decide which rows to process
			int rowStart = 1;
			int rowEnd = 53;

			for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
				Row r = sheet.getRow(rowNum);
				if (r == null) {
					// This whole row is empty
					// Handle it as needed
					continue;
				}
				Product product = new Product();
				List<AdditionalDetail> productDetails = new ArrayList<>();
				int lastColumn = Math.max(r.getLastCellNum(), FIELD_NAMES.length);
				// populate product details
				for (int cn = 0; cn < lastColumn; cn++) {
					Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
					if (c == null) {
						// The spreadsheet is empty in this cell
					} else {
						// Do something useful with the cell's contents
						AdditionalDetail e = new AdditionalDetail();
						e.setName(FIELD_NAMES[cn]);
						e.setValue(c.getCellTypeEnum() == CellType.NUMERIC ? "" + c.getNumericCellValue()
								: c.getStringCellValue());
						productDetails.add(e);
					}
				}
				product.setProductDetails(productDetails);
				StringBuilder concatenatedName = new StringBuilder();

				concatenatedName.append(r.getCell(6, Row.RETURN_BLANK_AS_NULL).getStringCellValue()).append(":")
						// .append(r.getCell(7, Row.RETURN_BLANK_AS_NULL).getNumericCellValue())
						// .append(":")
						// .append(r.getCell(4,
						// MissingCellPolicy.RETURN_BLANK_AS_NULL).getStringCellValue())
						// .append(":")
						.append(r.getCell(3, Row.RETURN_BLANK_AS_NULL).getStringCellValue());
				product.setName(concatenatedName.toString());

				int price = retrieveMoney(r, 9);
				product.setPrice(price);
				product.setPublicPriceTTC(retrieveMoney(r, 15));
				product.setPublicPriceHT(retrieveMoney(r, 14));
				product.setWholeSalePriceHT(retrieveMoney(r, 11));
				product.setWholesalePriceTTC(retrieveMoney(r, 12));
				product.setPuht(retrieveMoney(r, 8));
				Stock stock = new Stock();
				stock.setCount(new Double(r.getCell(10, Row.RETURN_BLANK_AS_NULL).getNumericCellValue()).intValue());
				product.setStock(stock);
				ProductWine pw = new ProductWine();
				pw.setTypeOfWine(r.getCell(5, Row.RETURN_BLANK_AS_NULL).getStringCellValue());
				proWineRepo.save(pw);
				product.setProductWine(pw);

				products.add(productsRepo.save(product));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int retrieveMoney(Row r, int i) {
		double value = r.getCell(i, Row.RETURN_BLANK_AS_NULL).getNumericCellValue();
		// round off to 2 d.p
		double valueRounded = Math.round(value * 100D) / 100D;
		// multiple by 100

		return new Double(valueRounded * 100).intValue();
	}

	private void createWine(WineRepository wineRepository) {
		Wine wine = new Wine();
		wine.setImage(new net.spark.cellar.Image());
		// wine.setAppellation(new Name());
		wine.setYear(2006);
		// wine.setCepage(TypeOfWine.getEmpty());
		wineRepository.save(wine);

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
		user = new User("delphine@aqui.mu", "Delphine", passwordEncoder.encode("delphine1234"), Role.ADMIN);
		user.setLocked(true);
		admin = userRepository.save(user);
		users.add(barista);
		users.add(admin);
		users.add(baker);
	}
}
