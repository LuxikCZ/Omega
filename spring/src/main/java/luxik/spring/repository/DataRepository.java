package luxik.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import luxik.spring.model.Data;
/**
 * Class for all SQL queries
 * @author luxik
 *
 */
@Repository
public interface DataRepository extends JpaRepository<Data, String>{ //SQL Query repository
	//average values in hours
	@Query(value="select avg(tempf) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageTempInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(dewptf) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageDewptInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(humidity) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageHumidityInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(windspeedmph) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageWindspeedInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(windgustmph) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageWindgustInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(winddir) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageWinddirInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(rainin) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageRainInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(dailyrainin) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageDailyrainInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(solarradiation) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageSolarradInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(UV) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageUVInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(indoortempf) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageIndoortempInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(indoorhumidity) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageIndoorhumInHour(@Param("hour")Integer hour, @Param("day")String day);
	@Query(value="select avg(baromin) from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0",
			nativeQuery = true)
	Float getAverageBaromInHour(@Param("hour")Integer hour, @Param("day")String day);
	
	//average values weekly
	@Query(value="select avg(tempf) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageTempInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(dewptf) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageDewptInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(humidity) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageHumidityInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(windspeedmph) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageWindspeedInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(windgustmph) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageWindgustInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(winddir) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageWinddirInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(rainin) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageRainInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(dailyrainin) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageDailyrainInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(solarradiation) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageSolarradInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(UV) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageUVInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(indoortempf) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageIndoortempInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(indoorhumidity) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageIndoorhumInWeek(@Param("date")String date, @Param("day")Integer day);
	@Query(value="select avg(baromin) from DataCelakovice "
			+ "where (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0",
			nativeQuery = true)
	Float getAverageBaromInWeek(@Param("date")String date, @Param("day")Integer day);
	
	//get available years
	@Query(value = "select distinct extract(year from date_ins) from DataCelakovice", nativeQuery = true)
	List<Integer> getYears();
	
	//get available months
	@Query(value = "select distinct extract(month from date_ins) from DataCelakovice", nativeQuery = true)
	List<Integer> getMonths();

	//average values in month
	@Query(value="select avg(tempf) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageTempInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(dewptf) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageDewptInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(humidity) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageHumidityInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(windspeedmph) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageWindspeedInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(windgustmph) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageWindgustInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(winddir) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageWinddirInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(rainin) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageRainInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(dailyrainin) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageDailyrainInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(solarradiation) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageSolarradInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(UV) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageUVInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(indoortempf) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageIndoortempInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(indoorhumidity) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageIndoorhumInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	@Query(value="select avg(baromin) from DataCelakovice "
			+ "where day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageBaromInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	
	//average values in year
	@Query(value="select avg(tempf) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageTempInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(dewptf) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageDewptInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(humidity) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageHumidityInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(windspeedmph) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageWindspeedInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(windgustmph) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageWindgustInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(winddir) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageWinddirInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(rainin) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageRainInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(dailyrainin) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageDailyrainInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(solarradiation) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageSolarradInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(UV) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageUVInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(indoortempf) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageIndoortempInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(indoorhumidity) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageIndoorhumInYear(@Param("month") Integer month, @Param("year") Integer year);
	@Query(value="select avg(baromin) from DataCelakovice "
			+ "where month(date_ins) = :month and year(date_ins) = :year",
			nativeQuery = true)
	Float getAverageBaromInYear(@Param("month") Integer month, @Param("year") Integer year);
	
	//newest values or record values
	@Query(value = "select tempf from DataCelakovice where"
			+ " date_ins = (select max(date_ins) from DataCelakovice)"
			, nativeQuery = true)
	Float getNewestTempOut();
	@Query(value = "select indoortempf from DataCelakovice where"
			+ " date_ins = (select max(date_ins) from DataCelakovice)"
			, nativeQuery = true)
	Float getNewestTempIn(); 
	@Query(value = "select max(tempf) from DataCelakovice", nativeQuery=true)
	Float getMaxTempOut();
	@Query(value = "select max(date_ins) from DataCelakovice where tempf ="
			+ " (select max(tempf) from DataCelakovice)"
			, nativeQuery = true)
	String getDateByMaxTempOut();
	@Query(value = "select max(indoortempf) from DataCelakovice", nativeQuery = true)
	Float getMaxTempIn();
	@Query(value = "select max(date_ins) from DataCelakovice where indoortempf"
			+ " = (select max(indoortempf) from DataCelakovice)"
			, nativeQuery = true)
	String getDateByMaxTempIn();
	@Query(value = "select max(rainin) from DataCelakovice", nativeQuery = true)
	Float getMaxRain();
	@Query(value = "select max(date_ins) from DataCelakovice where rainin "
			+ "= (select max(rainin) from DataCelakovice)"
			,nativeQuery = true)
	String getDateByMaxRain();
	@Query(value = "select max(windspeedmph) from DataCelakovice", nativeQuery = true)
	Float getMaxWind();
	@Query(value = "select date_ins from DataCelakovice where windspeedmph ="
			+ " (select max(windspeedmph) from DataCelakovice)"
			, nativeQuery = true)
	String getDateByMaxWind();
	@Query(value = "select min(tempf) from DataCelakovice", nativeQuery = true)
	Float getMinTempOut();
	@Query(value = "select max(date_ins) from DataCelakovice where tempf ="
			+ " (select min(tempf) from DataCelakovice)"
			, nativeQuery = true)
	String getDateByMinTempOut();
	@Query(value = "select min(indoortempf) from DataCelakovice", nativeQuery = true)
	Float getMinTempIn();
	@Query(value = "select max(date_ins) from DataCelakovice where indoortempf ="
			+ " (select min(indoortempf) from DataCelakovice)"
			, nativeQuery = true)
	String getDateByMinTempIn();
}//end interface
