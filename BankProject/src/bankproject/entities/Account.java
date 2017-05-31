package bankproject.entities;

import bankproject.enumerations.CountryEnum;

public class Account {
	

	private Integer id;
	private String number;
	private Customer customer;
	private Double summary;
	private CountryEnum Country;

//	public static void main(String[] args) throws SQLException {
//
//		String str = buildNumber(CountryEnum.BRITAIN);
//		System.out.println(str);
//	}

	public static String buildNumber(CountryEnum country) {

		StringBuilder sb = new StringBuilder();
		int digit, i;

		switch (country) {

		case SPAIN:
			sb.append("ES");
			break;
		case FRANCE:
			sb.append("FR");
			break;
		case BRITAIN:
			sb.append("GB");
			break;
		case GERMANY:
			sb.append("DE");
			break;
		case BELGIUM:
			sb.append("BE");
			break;
		case NEDERLANDS:
			sb.append("NL");
			break;
		}

		for (i = 1; i < 7; i++) {

			digit = (int) (Math.random() * 10);
			sb.append(digit);

		}

		return sb.toString();
	}

}
