package com.example.hrback.model.interview;

import com.example.hrback.model.participant.Participant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "video", schema = "hr_back")
public class Video {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "video_seq")
    @SequenceGenerator(name = "video_seq", initialValue = 1, allocationSize = 1, sequenceName = "video_id_seq")
    @JsonIgnore
    private Long id;
    private String name;
    private LocalDateTime uploaded;
    private String question;
    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private Participant participant;
    private String comment;
    private Long position;

    public Video(String name, LocalDateTime uploaded, String question, Participant participant, String comment, Long position) {
        this.name = name;
        this.uploaded = uploaded;
        this.question = question;
        this.participant = participant;
        this.comment = comment;
        this.position = position;
    }

    public Video() {

    }
}
