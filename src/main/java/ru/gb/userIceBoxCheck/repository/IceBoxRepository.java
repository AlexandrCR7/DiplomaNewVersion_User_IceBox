package ru.gb.userIceBoxCheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.userIceBoxCheck.model.IceBox;

@Repository
public interface IceBoxRepository extends JpaRepository<IceBox, Long> {
}
