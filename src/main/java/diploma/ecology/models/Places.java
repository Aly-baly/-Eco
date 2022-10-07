package diploma.ecology.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "recycle_place")
public class Places {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "worked")
    private boolean worked;

    public Places(String address, String name, boolean worked) {
        this.address = address;
        this.name = name;
        this.worked = worked;
    }
}
