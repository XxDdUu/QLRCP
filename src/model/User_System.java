package model;

public class User_System {
	    private String username;
	    private String password;
	    private String userRole;
	    private String MATv;
	    private String IDStaff;
	    private String usermail;
	    
		public User_System(String username, String password, String userRole, String mATv, String iDStaff,
				String usermail) {
			super();
			this.username = username;
			this.password = password;
			this.userRole = userRole;
			MATv = mATv;
			IDStaff = iDStaff;
			this.usermail = usermail;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getUserRole() {
			return userRole;
		}
		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}
		public String getMATv() {
			return MATv;
		}
		public void setMATv(String mATv) {
			MATv = mATv;
		}
		public String getIDStaff() {
			return IDStaff;
		}
		public void setIDStaff(String iDStaff) {
			IDStaff = iDStaff;
		}
		public String getUsermail() {
			return usermail;
		}
		public void setUsermail(String usermail) {
			this.usermail = usermail;
		}

	    
	}

