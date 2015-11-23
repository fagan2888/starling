/**
 * Copyright (C) 2014-Present McLeod Moores Software Limited.  All rights reserved.
 */
package com.mcleodmoores.integration.adapter;

import java.util.Objects;

import net.finmath.time.businessdaycalendar.BusinessdayCalendar;

import com.opengamma.analytics.date.WorkingDayCalendar;
import com.opengamma.util.ArgumentChecker;

/**
 * Wraps a {@link WorkingDayCalendar} for {@link BusinessdayCalendar}.
 */
public class BusinessDayCalendarAdapter extends BusinessdayCalendar {
  /** The calendar */
  private final WorkingDayCalendar _calendar;

  /**
   * Creates an instance.
   * @param calendar The calendar, not null
   */
  public BusinessDayCalendarAdapter(final WorkingDayCalendar calendar) {
    _calendar = ArgumentChecker.notNull(calendar, "calendar");
  }

  @Override
  public boolean isBusinessday(final java.util.Calendar date) {
    return _calendar.isWorkingDay(FinmathDateUtils.convertToLocalDate(date));
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _calendar.getName().hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof BusinessDayCalendarAdapter)) {
      return false;
    }
    final BusinessDayCalendarAdapter other = (BusinessDayCalendarAdapter) obj;
    return Objects.equals(_calendar.getName(), other._calendar.getName());
  }

}