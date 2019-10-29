package seedu.address.model.calendar;

import java.time.Duration;
import java.util.Iterator;

import seedu.address.model.member.MemberName;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.PeriodList;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.*;

import org.apache.commons.lang3.time.DateUtils;

public class CalendarWrapper {
    private final MemberName memberName;
    private final Calendar calendar;

    public CalendarWrapper (MemberName memberName, Calendar calendar) {
        this.memberName = memberName;
        this.calendar = calendar;
    }

    public MemberName getMemberName() {
        return memberName;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public boolean isSameCalendar(CalendarWrapper otherCalendar) {
        if (otherCalendar == this) {
            return true;
        }

        return otherCalendar != null
                && otherCalendar.getMemberName().equals(getMemberName())
                && otherCalendar.getCalendar().equals(getCalendar());
    }

    public boolean hasMemberName(MemberName otherCalendar) {
        if (otherCalendar == getMemberName()) {
            return true;
        }

        return otherCalendar != null
                && otherCalendar.equals(getMemberName());
    }

    public PeriodList getEventsDuringPeriod(Period period) {
        PeriodList mainPeriodList = new PeriodList();
        for (Object o : calendar.getComponents("VEVENT")) {
            VEvent event = (VEvent) o;
            mainPeriodList = mainPeriodList.add(event.calculateRecurrenceSet(period));
        }
        return mainPeriodList;
    }

    public PeriodList getFreeTimeDuringPeriod(Period period, Duration duration) {
        PeriodList tempPeriodList = new PeriodList();
        PeriodList resultList = new PeriodList();
        tempPeriodList.add(period);
        PeriodList busyTimePeriodList = getEventsDuringPeriod(period);

        System.out.println("busy time");
        for (Object po : busyTimePeriodList) {
            System.out.println((Period)po);
        }
        PeriodList freeTimePeriodList = tempPeriodList.subtract(busyTimePeriodList);

        System.out.println("free time");
        for (Object po : freeTimePeriodList) {
            System.out.println((Period)po);
        }
        System.out.println("end of free time");
        Iterator<Period> freeTimeIter = freeTimePeriodList.iterator();
        while (freeTimeIter.hasNext()) {
            Period currentPeriod = freeTimeIter.next();
            Duration currentPeriodDuration = Duration.parse(currentPeriod.getDuration().toString());
            if (currentPeriodDuration.compareTo(duration) > 0) {
                resultList.add(currentPeriod);
            }
        }
        return resultList;
    }
}
