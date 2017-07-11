package net.jiniguru.roshan.backend;

import org.springframework.data.jpa.repository.JpaRepository;

import net.jiniguru.roshan.backend.data.entity.HistoryItem;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
}
