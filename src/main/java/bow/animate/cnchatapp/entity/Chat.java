package bow.animate.cnchatapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "chats")
    private List<User>users;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "chat")
    private List<ChatMessage>chatMessages;

}
