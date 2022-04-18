package luxik.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import luxik.spring.model.Data;

@Repository
public interface DataRepository extends JpaRepository<Data, String>{ //SQL Query repository
	@Query(value="select * from DataCelakovice"
		+ " where date_ins = (select max(date_ins) from DataCelakovice)", nativeQuery=true)
	List<Data> getNewestData();
	
	@Query(value="select distinct tempf, date_ins, dewptf, humidity, windspeedmph, windgustmph,"
			+ " winddir, rainin, dailyrainin, solarradiation, UV, indoortempf,indoorhumidity, baromin"
			+ " from DataCelakovice where hour(date_ins) =:hour and datediff(date_ins, :day) = 0 group by tempf"
			, nativeQuery=true)
	List<Data> getDistinctDataInHour(@Param("hour")Integer hour, @Param("day")String day);
	
	@Query(value = "select distinct tempf, date_ins, dewptf, humidity, windspeedmph, windgustmph,"
			+ " winddir, rainin, dailyrainin, solarradiation, UV, indoortempf, baromin,"
			+ "indoorhumidity from DataCelakovice where"
			+ " (select datediff(date_ins, date_add(:date, INTERVAL :day DAY))) = 0 group by tempf"
			, nativeQuery = true)
	List<Data> getDistinctWeekly(@Param("date") String date, @Param("day") Integer Day);
	
	@Query(value = "select distinct extract(year from date_ins) from DataCelakovice", nativeQuery = true)
	List<Integer> getYears();
	
	@Query(value = "select distinct extract(month from date_ins) from DataCelakovice", nativeQuery = true)
	List<Integer> getMonths();
	
	@Query(value = "select distinct tempf, date_ins, dewptf, humidity, windspeedmph, windgustmph,"
			+ " winddir, rainin, dailyrainin, solarradiation, UV, indoortempf, baromin,"
			+ "indoorhumidity from DataCelakovice where"
			+ " day(date_ins) = :day and month(date_ins) = :month and year(date_ins) = :year"
			+ " group by tempf"
			, nativeQuery = true)
	List<Data> getDataInMonth(@Param("month") Integer month, @Param("year") Integer year,
			@Param("day") Integer day);
	
	@Query(value = "select distinct tempf, date_ins, dewptf, humidity, windspeedmph, windgustmph,"
			+ " winddir, rainin, dailyrainin, solarradiation, UV, indoortempf, baromin,"
			+ "indoorhumidity from DataCelakovice where"
			+ " month(date_ins) = :month and year(date_ins) = :year"
			+ " group by tempf"
			, nativeQuery = true)
	List<Data> getDataInYear(@Param("month") Integer month, @Param("year") Integer year);
	
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
