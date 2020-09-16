package com.adpmx.quizter.model;

import javax.persistence.*;

@Entity
public class Usuarios {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userId;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private boolean enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_grupo", nullable = false)
    private Grupos grupo;

    public Usuarios() {
    }

    public Usuarios(Long userId, String username, String password, boolean enabled, Grupos grupo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.grupo = grupo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupos grupo) {
        this.grupo = grupo;
    }
}
