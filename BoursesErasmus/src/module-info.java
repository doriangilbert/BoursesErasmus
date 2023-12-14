module BoursesErasmus {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens fr.univtours.polytech.bourseserasmus to javafx.graphics, javafx.fxml, javafx.base;
}
