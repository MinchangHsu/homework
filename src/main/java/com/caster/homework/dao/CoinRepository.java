package com.caster.homework.dao;

import com.caster.homework.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Long> {
}
