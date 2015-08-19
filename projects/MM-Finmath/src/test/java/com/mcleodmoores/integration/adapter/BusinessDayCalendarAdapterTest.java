/**
 * Copyright (C) 2014-Present McLeod Moores Software Limited.  All rights reserved.
 */
package com.mcleodmoores.integration.adapter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import net.finmath.time.businessdaycalendar.BusinessdayCalendar;

import org.testng.annotations.Test;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;

import com.opengamma.analytics.date.SimpleWorkingDayCalendar;
import com.opengamma.analytics.date.WorkingDayCalendar;

/**
 * Unit tests for {@link BusinessDayCalendarAdapter}.
 */
@Test
public class BusinessDayCalendarAdapterTest {
  /** Holiday dates */
  private static final Collection<LocalDate> HOLIDAYS;
  /** A test calendar */
  private static final WorkingDayCalendar CALENDAR;
  /** The adapter */
  private static final BusinessdayCalendar ADAPTER;

  static {
    HOLIDAYS = new HashSet<>();
    for (int i = 1; i < 13; i++) {
      HOLIDAYS.add(LocalDate.of(2014, i, 1));
    }
    CALENDAR = new SimpleWorkingDayCalendar("Test", HOLIDAYS, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    ADAPTER = new BusinessDayCalendarAdapter(CALENDAR);
  }

  /**
   * Tests the hashCode and equals methods.
   */
  @Test
  public void testHashCodeEquals() {
    BusinessDayCalendarAdapter adapter = new BusinessDayCalendarAdapter(
        new SimpleWorkingDayCalendar("Test", HOLIDAYS, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    assertEquals(ADAPTER, ADAPTER);
    assertNotEquals(null, ADAPTER);
    assertNotSame(ADAPTER, adapter);
    assertEquals(ADAPTER.hashCode(), adapter.hashCode());
    assertEquals(ADAPTER, adapter);
    adapter = new BusinessDayCalendarAdapter(
        new SimpleWorkingDayCalendar("Test", Collections.<LocalDate>emptySet(), DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    // only using the name in these methods
    assertEquals(ADAPTER.hashCode(), adapter.hashCode());
    assertEquals(ADAPTER, adapter);
    adapter = new BusinessDayCalendarAdapter(
        new SimpleWorkingDayCalendar("Test1", HOLIDAYS, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    assertNotEquals(ADAPTER, adapter);
  }

  /**
   * Tests that holidays are identified correctly.
   */
  @Test
  public void test() {
    final LocalDate end = LocalDate.of(2015, 1, 1);
    LocalDate date = LocalDate.of(2014, 1, 1);
    while (date.isBefore(end)) {
      if (HOLIDAYS.contains(date) || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
        assertFalse(ADAPTER.isBusinessday(FinmathDateUtils.convertLocalDate(date)));
      } else {
        assertTrue(ADAPTER.isBusinessday(FinmathDateUtils.convertLocalDate(date)));
      }
      date = date.plusDays(1);
    }
  }

}