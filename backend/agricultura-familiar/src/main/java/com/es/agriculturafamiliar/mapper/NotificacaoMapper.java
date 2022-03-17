package com.es.agriculturafamiliar.mapper;
import com.es.agriculturafamiliar.dto.NotificacaoDTO;
import com.es.agriculturafamiliar.entity.Notificacao;

import org.mapstruct.Mapper;

@Mapper
public interface NotificacaoMapper {
    Notificacao NotificacaoDTOToNotificacao(NotificacaoDTO notificacaoDTO);
    NotificacaoDTO NotificacaoToNotificacaoDTO(Notificacao notificacao);    
}
