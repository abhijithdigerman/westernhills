package com.westernhills.westernhills.entity.admin;


import com.westernhills.westernhills.entity.SuperEntity;
import com.westernhills.westernhills.entity.userEntity.CheckOut;
import com.westernhills.westernhills.entity.userEntity.OrderStatus;
import com.westernhills.westernhills.entity.userEntity.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class StockUpdate extends SuperEntity {





    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;


    private Long returnStock;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;



    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;




    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    @ManyToOne
    @JoinColumn(name = "checkOut_id")
    private CheckOut checkOut;




}
