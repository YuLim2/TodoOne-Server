package com.todoone.controller;

import com.todoone.domain.Calendar;
import com.todoone.domain.Todo;
import com.todoone.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CalendarController {
    @Autowired
    private final CalendarRepository calendarRepository;
    public CalendarController(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @RequestMapping("/calendar")
    public String Calendar(@RequestBody Calendar calendar) throws Exception{
        calendarRepository.addCalendar(calendar);
        return "성공 : Calendar 추가 완료";
    }

    @RequestMapping(value = "/getCalendar", method = RequestMethod.GET)
    public List<Todo> GetCalendar(@RequestParam int couple_id) throws Exception{
        return calendarRepository.getCalendarByCoupleId(couple_id);
    }

    @RequestMapping(value = "/deleteCalendar", method = RequestMethod.DELETE)
    public String DeleteTodo(@RequestParam int todo_id) throws Exception{
        calendarRepository.deleteCalendar(todo_id);
        return "성공: Calendar 삭제 완료";
    }
}
