package net.spark.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import net.spark.backend.data.entity.HistoryItem;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
}
