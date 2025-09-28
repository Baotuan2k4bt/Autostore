
package com.example.autostore.repository;

import com.example.autostore.Enum.CarStatus;
import com.example.autostore.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICarRepository extends JpaRepository<Car, Integer> {

    @Query("""
           SELECT c FROM Car c
           WHERE (:carId IS NOT NULL AND c.carId = :carId)
              OR (:carId IS NULL AND (:keyword IS NULL OR LOWER(c.carName) LIKE LOWER(CONCAT('%', :keyword, '%'))))
           """)
    Page<Car> findByKeyword(@Param("keyword") String keyword,
                            @Param("carId") Integer carId,
                            Pageable pageable);
    // ✅ Đếm tổng số xe
    long count();

    // ✅ Đếm số xe theo status (ví dụ: MAINTENANCE, AVAILABLE, …)
    long countByStatus(CarStatus status);

    @Query(value = """
        SELECT LOWER(br.brandName) AS brandName,
               COUNT(c.carId) AS carCount
        FROM Car c
        JOIN Brand br ON br.brandId = c.brandId
        JOIN CarType ct ON ct.carTypeId = c.carTypeId
        WHERE (:CarType IS NULL OR LOWER(ct.typeName) = LOWER(:carType))
        GROUP BY LOWER(br.brandName)
        ORDER BY brandName
        """, nativeQuery = true)
    List<Object[]> getBrandRatio(@Param("carType") String carType);

    @Query("SELECT c FROM Car c WHERE c.isFeatured = true AND c.status = 'AVAILABLE'")
    List<Car> findFeaturedCars();
}
