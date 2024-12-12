package database;

import model.hotel.Hotel;
import model.hotel.RoomType;
import model.reservation.Card;
import model.reservation.Cash;
import model.reservation.EWallet;
import model.reservation.PaidState;
import model.reservation.PaymentMethod;
import model.reservation.Reservation;
import model.reservation.ReservationState;
import model.reservation.UnPaidState;
import model.user.Admin;
import model.user.Customer;
import model.user.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
	String jdbcURL = "jdbc:mysql://localhost:3306/aol_fla";
	String user = "root";
	String password = "";
	Connection connection;
	PreparedStatement ps;
	
	Database() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean addUser(User user) {
		
		String query = "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?);";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPhoneNo());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			
			if(user instanceof Admin) {
				ps.setString(6, "admin");
			} else {
				ps.setString(6, "customer");
			}
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e.getMessage());
		}
		
		return false;
	}
	
	public List<String> getFacilitiesByHotelID(String hotelID) {
		
		List<String> facilities = new ArrayList<>();
		
		String query = "SELECT * FROM facilities WHERE hotelID = ?;";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotelID);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				facilities.add(rs.getString(2));
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return facilities;
		
	}
	
	public List<RoomType> getRoomTypesByHotelID(String hotelID) {
		
		// TODO: Add Functionality
		
		List<RoomType> roomTypes = new ArrayList<>();
		
		String query = "SELECT * FROM roomtype WHERE hotelID = ?;";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotelID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				RoomType roomType = new RoomType(rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getBoolean(5), rs.getInt(6));
				roomTypes.add(roomType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roomTypes;
		
	}
	
	public Hotel getHotelByAdminID(String adminID) {
		String query = "SELECT * FROM hotel_admin WHERE adminID = ?;";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, adminID);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {				
				String hotelID = rs.getString(1);
				
				query = "SELECT * FROM hotels WHERE id = ?;";
				
				ps = connection.prepareStatement(query);
				ps.setString(1, hotelID);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					return new Hotel(hotelID, rs.getString(2), rs.getString(3), rs.getDouble(4), getFacilitiesByHotelID(hotelID), getRoomTypesByHotelID(hotelID));
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public User getUserByCredentials(String email, String password) {
		
		String query = "SELECT * FROM users WHERE email = ? AND BINARY password = ?;";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String phoneNo = rs.getString(3);
				String role = rs.getString(6);
				
				if(role.equals("customer")) {
					return new Customer(id, name, phoneNo, email, password);
				} else {
					Admin admin = new Admin(id, name, phoneNo, email, password);
					
					admin.setHotel(getHotelByAdminID(id));
					
					return admin;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Hotel addHotel(Hotel hotel) {
		
		String query = "INSERT INTO hotels VALUES (?, ?, ?, ?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotel.getId());
			ps.setString(2, hotel.getName());
			ps.setString(3, hotel.getAddress());
			ps.setDouble(4, hotel.getRating());
			ps.execute();
			return hotel;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public Admin addAdminHotel(Admin admin, Hotel hotel) {
		String query = "INSERT INTO hotel_admin VALUES (?, ?);";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotel.getId());
			ps.setString(2, admin.getId());
			ps.execute();
			return admin;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String addHotelFacility(String hotelID, String facility) {
		
		String query = "INSERT INTO facilities VALUES(?, ?);";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotelID);
			ps.setString(2, facility);
			
			ps.execute();
			return facility;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public boolean removeFacility(String hotelID, String facility) {
		String query = "DELETE FROM facilities WHERE hotelID = ? AND name = ?";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotelID);
			ps.setString(2, facility);
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addRoomType(RoomType roomType, Hotel hotel) {
		
		String query = "INSERT INTO `roomtype` VALUES (?,?,?,?,?,?)";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotel.getId());
			ps.setString(2, roomType.getName());
			ps.setDouble(3, roomType.getPrice());
			ps.setString(4, roomType.getDescription());
			ps.setBoolean(5, roomType.isNonSmoking());
			ps.setInt(6, roomType.getRoomAmount());
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean removeRoomType(String hotelID, String roomTypeName) {
		String query = "DELETE FROM roomtype WHERE hotelID = ? AND name = ?";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotelID);
			ps.setString(2, roomTypeName);
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Customer getCustomerByID(String customerID) {
		
		String query = "SELECT * FROM users WHERE id = ?;";
		Customer customer = null;
		
		try {
			ps = connection.prepareStatement(query);
			
			ps.setString(1, customerID);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customer;
		
	}
	
	public RoomType getRoomType(String hotelID, String typeName) {
		RoomType roomType = null;
		
		String query = "SELECT * FROM roomtype WHERE hotelID = ? AND name = ?";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotelID);
			ps.setString(2, typeName);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				roomType = new RoomType(typeName, rs.getDouble(3), rs.getString(4), rs.getBoolean(5), rs.getInt(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roomType;
	}
	
	public List<Reservation> getReservationByHotel(Hotel hotel) {
		
		List<Reservation> reservationList = new ArrayList<>();
		
		String query = "SELECT * FROM reservation WHERE hotelID = ?;";
		
		try {
			ps = connection.prepareStatement(query);			
			ps.setString(1, hotel.getId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				RoomType roomType = getRoomType(hotel.getId(), rs.getString(4));
				Customer customer = getCustomerByID(rs.getString(3));
				ReservationState state = 
						"Paid".equals(rs.getString(8))
						? new PaidState() : new UnPaidState();
				PaymentMethod paymentMethod;
				
				switch(rs.getString(9)) {
				case "Cash":
					paymentMethod = new Cash();
					break;
				case "Card":
					paymentMethod = new Card();
					break;
				default:
					paymentMethod = new EWallet();
				}
				
				Reservation reservation = new Reservation(rs.getString(1), roomType, customer, hotel, rs.getDate(5), rs.getDate(6), rs.getInt(7), state, paymentMethod);
				reservationList.add(reservation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reservationList;
		
	}
	
	public List<String> getCustomersID() {
		
		List<String> customerIDs = new ArrayList<>();
		
		String query = "SELECT id FROM users WHERE role = 'customer';";
		
		try {
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				customerIDs.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return customerIDs;
		
	}
	
	public Reservation addReservation(Reservation reservation) {
		
		String query = "INSERT INTO reservation VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, reservation.getId());
			ps.setString(2, reservation.getHotel().getId());
			ps.setString(3, reservation.getCustomer().getId());
			ps.setString(4, reservation.getRoomType().getName());
			ps.setDate(5, new Date(reservation.getCheckInDate().getTime()));
			ps.setDate(6, new Date(reservation.getCheckOutDate().getTime()));
			ps.setInt(7, reservation.getRoomAmount());
			ps.setString(8, reservation.getState().toString());
			ps.setString(9, reservation.getPaymentMethod().toString());
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reservation = null;
		}		
		
		return reservation;
		
	}

	public boolean removeReservation(String id) {
		String query = "DELETE FROM reservation WHERE id = ?;";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, id);
			
			ps.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public void updateReservationStatus(String reservationID, ReservationState newStatus) {
		
		String query = "UPDATE reservation SET state = ? WHERE id = ?;";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, newStatus.toString());
			ps.setString(2, reservationID);
			
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Reservation> getReservationByCustomer(Customer customer) {
		List<Reservation> reservationList = new ArrayList<>();
		
		String query = "SELECT * FROM reservation WHERE customerID = ?;";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, customer.getId());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Hotel hotel = getHotelByID(rs.getString(2));
				RoomType roomType = getRoomType(hotel.getId(), rs.getString(4));
				ReservationState state = 
						"Paid".equals(rs.getString(8))
						? new PaidState() : new UnPaidState();
				PaymentMethod paymentMethod;
				
				switch(rs.getString(9)) {
				case "Cash":
					paymentMethod = new Cash();
					break;
				case "Card":
					paymentMethod = new Card();
					break;
				default:
					paymentMethod = new EWallet();
				}
				
				Reservation reservation = new Reservation(rs.getString(1), roomType, customer, hotel, rs.getDate(5), rs.getDate(6), rs.getInt(7), state, paymentMethod);
				reservationList.add(reservation);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return reservationList;
	}

	public Hotel getHotelByID(String hotelID) {
		
		Hotel hotel = null;
		
		String query = "SELECT * FROM hotels WHERE id = ?;";
		
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, hotelID);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				hotel = new Hotel(hotelID, rs.getString(2), rs.getString(3), rs.getInt(4), getFacilitiesByHotelID(hotelID), getRoomTypesByHotelID(hotelID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hotel;
	}

	public List<String> getAllHotelID() {
		List<String> hotelIDs = new ArrayList<>();
		
		String query = "SELECT * FROM hotels";
		
		try {
			ps = connection.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				hotelIDs.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return hotelIDs;
	}
}