package bow.animate.cnchatapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "chat_message")
public class ChatMessage{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;


    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "send_date")
    private ZonedDateTime sendDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",insertable=false, updatable=false)
    private User sender;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id",insertable=false, updatable=false)
    private Chat chat;
}
