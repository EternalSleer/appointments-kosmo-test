package com.hospital.appointments.controllers;


import com.hospital.appointments.model.Appointment;
import com.hospital.appointments.repository.AppointmentRepository;
import com.hospital.appointments.repository.DoctorRepository;
import com.hospital.appointments.repository.RoomRepository;
import com.hospital.appointments.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public String listAppointments(
            @RequestParam(required = false) Long doctorId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model) {

        //List<Appointment> appointments = appointmentService.findAll();
        model.addAttribute("appointments", appointmentRepository.findAll());
        model.addAttribute("doctors", doctorRepository.findAll());

        return "appointments";
    }

    @GetMapping("/new")
    public String newAppointment(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", doctorRepository.findAll());
        model.addAttribute("rooms", roomRepository.findAll());
        return "new-appointment";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute Appointment appointment, RedirectAttributes redirectAttributes, Model model) {
        try {
            appointmentService.createAppointment(appointment);
            redirectAttributes.addFlashAttribute("success", "Cita agendada correctamente.");
        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("doctors", doctorRepository.findAll());
            model.addAttribute("rooms", roomRepository.findAll());
            return "new-appointment";
        }
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String editAppointment(@PathVariable Long id, Model model) {
//        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
//        model.addAttribute("appointment", appointment);
//        model.addAttribute("doctors", doctorRepository.findAll());
//        model.addAttribute("rooms", roomRepository.findAll());
        return "new-appointment";
    }

    @GetMapping("/delete/{id}")
    public String cancelAppointment(@PathVariable Long id) {
//        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }
}