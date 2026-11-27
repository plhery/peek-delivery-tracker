---
title: Rastreamento Aras Kargo: códigos e status em turco
description: Rastreie encomendas Aras Kargo na Turquia: formato dos códigos de 13 dígitos, significado de status em turco como TESLİMAT ŞUBESİNDE e uso no Peek.
slug: aras-kargo-tracking
published: 2026-11-26
updated: 2026-11-26
---

A Aras Kargo (também grafada Aras Cargo) é uma transportadora da Turquia. O rastreador da Aras, conforme lido pelo Peek, registra todas as etapas em turco. A loja ou o remetente informa o código de rastreamento: cole-o na caixa acima. O rastreador próprio da Aras Kargo fica em kargotakip.araskargo.com.tr. As tabelas abaixo explicam seus status em turco.

## Código de rastreamento da Aras Kargo: como ele se parece

O código do Peek reconhece os números da Aras Kargo como sequências de 13 dígitos, associando à Aras os que começam por `60` ou `65`. Com dígitos fictícios:

| Número (fictício) | O que é |
| --- | --- |
| `6048291736542` | 13 dígitos começando por `60`: formato que o Peek vincula à Aras Kargo. [Delhivery](carrier:delhivery), Correos Chile, Yunda e Korea Post também usam esse formato. |
| `6548291736542` | 13 dígitos começando por `65`: o mesmo. |
| `1482917365482` | 13 dígitos com outro início: o Peek não os associa à Aras. Se a loja indicar Aras Kargo, escolha Aras Kargo você mesmo (veja abaixo). |
| `604829173654` | Apenas 12 dígitos. Se você escolher Aras Kargo para ele, o Peek informa que ela "não emite códigos como este". Verifique se faltou um dígito. |

Espaços não interferem ao colar o número no Peek. Um link do rastreador da Aras contendo o código (`kargotakip.araskargo.com.tr/mainpage.aspx`, com `code=` e o número) também funciona.

**Onde encontrar.** No e-mail de envio da loja ou na página do pedido. Se não encontrar, veja [como encontrar seu código de rastreamento](guide:find-tracking-number) e [formatos de código de rastreamento](guide:tracking-number-formats).

## Status de rastreamento da Aras Kargo explicados

O rastreador da Aras exibe o status atual do pacote (`Son Durum`), um dia previsto de entrega e a lista de movimentações em veículos (`Araç Hareketleri`). Cada movimentação traz data e hora (`İşlem Tarihi`), província (`İl`), unidade (`Birim` — agência ou centro de transferência) e descrição (`İşlem Açıklaması`). Quando ocorrem, também lista os dias em que a entrega não foi concluída e eventual redirecionamento para outra agência.

**Status atual (no topo):**

| A Aras informa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `ÇIKIŞ ŞUBESİNDE` | Na agência de origem que recebeu a encomenda. | "Recebida pela transportadora" |
| `YOLDA` | A caminho, entre os centros de transferência da Aras. | "Em trânsito" |
| `TESLİMAT ŞUBESİNDE` | Na agência de entrega responsável por levar o pacote até você. Permanece assim mesmo após uma entrega não realizada. | "Em trânsito" |
| `TESLİM EDİLDİ` | Entregue. A Aras informa a data e a hora. | "Entregue" |

**Movimentações em veículos (carregamento, descarregamento ou ensacamento):**

| A Aras informa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `ŞUBEDE ARAÇ YÜKLEME` | Carregada em um veículo em uma agência. | "Em trânsito" |
| `ŞUBEDE ARAÇ İNDİRME` | Descarregada de um veículo em uma agência. | "Em trânsito" |
| `AKTARMADA HAT ARACI YÜKLEME` | Carregada em um veículo de longa distância em um centro de transferência. | "Em trânsito" |
| `AKTARMADA HAT ARACI İNDİRME` | Descarregada de um veículo de longa distância em um centro de transferência. | "Em trânsito" |
| `AKTARMADA SUBE ARACI YUKLEME` | Carregada no veículo de uma agência em um centro de transferência. | "Em trânsito" |
| `AKTARMADA SUBE ARACI INDIRME` | Descarregada no centro de transferência do veículo de uma agência. | "Em trânsito" |
| `AKTARMADA TORBA DOLDURMA` | Colocada em malote junto a outras encomendas em um centro de transferência. | "Em trânsito" |

O rastreador escreve as duas linhas com `SUBE` sem acentos turcos, exatamente como mostrado acima.

**Entrega não realizada (`Teslim Edilememe Nedeni`, o motivo). Uma linha por dia:**

| A Aras informa | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `TESLİMAT GÜN İÇERİSİNDE YAPILAMADI` | A encomenda não pôde ser entregue naquele dia. | "Tentativa de entrega" |
| `TATİL DOLAYISIYLA TESLİMAT YAPILAMADI` | Sem entrega devido a feriado. | "Tentativa de entrega" |
| `UĞRAMA NOTU BIRAKILDI (1-2)` | O entregador esteve no local e deixou aviso. | "Tentativa de entrega" |
| `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` | Seu endereço fica fora da área de entrega da agência. | "Tentativa de entrega" |

**Redirecionamento.** `YÖNLENDİRİLDİ` significa que o pacote foi encaminhado a outra agência. A Aras informa o motivo e o Peek o acrescenta entre parênteses, exibindo "Em trânsito".

Esses dois últimos tipos de registro trazem apenas data, sem horário. O rastreador da Aras também pode exibir uma nota do entregador ao lado de uma entrega não realizada, que o Peek omite. Saiba mais em [o que significam os status de rastreamento](guide:tracking-statuses).

## Entrega Aras Kargo na Turquia

Uma encomenda segue da agência de origem da Aras, passa por centros de transferência (linhas com `AKTARMADA`) e chega à agência de entrega (`TESLİMAT ŞUBESİNDE`), que a leva até o seu endereço.

**Dia previsto de entrega.** O rastreador da Aras informa uma data planejada. O Peek mostra essa data como entrega prevista enquanto o pacote está a caminho, removendo-a após uma entrega não realizada ou depois que o dia já passou.

**Fora da área de entrega.** `ALICI ADRESİ DAĞITIM ALANI DIŞINDA` significa que a agência não atende seu endereço: pergunte à loja qual será o próximo passo.

**O que esta página não cobre.** O Peek não conseguiu ler as páginas institucionais da Aras Kargo, portanto esta página não traz prazos de entrega, número de tentativas, períodos de guarda nem regras de retirada. O rastreador da Aras, como lido pelo Peek, não indica ponto de retirada. Para uma entrega não realizada, veja [entrega não realizada](guide:missed-delivery) e [encomendas não retiradas](guide:uncollected-parcel).

## Rastreamento Aras Kargo parado, extravio ou devolução

**Sem resultado.** Quando a Aras não reconhece o número, o rastreador responde `Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` (nenhum resultado encontrado). A loja pode ainda não ter entregue o pacote. O Peek continua consultando: uma encomenda vinculada à Aras Kargo exibe "Aguardando Aras Kargo". Confira os 13 dígitos com a loja; se nada surgir após alguns dias, pergunte se ela realmente entregou o volume à Aras.

**Parado em `TESLİMAT ŞUBESİNDE`.** A agência de entrega está com o pacote, e esse status não muda após uma entrega não realizada. Se houver uma linha de motivo listada para algum dia (tabela acima), o pacote não foi entregue naquele dia e o texto explica o porquê. Caso contrário, aguarda entrega lá. Como o Peek não recebe uma etapa própria de "saiu para entrega" da Aras, o envio permanece "Em trânsito" até ser entregue ou até uma entrega não realizada.

**Sem atualização há dias.** Quando uma encomenda em trânsito fica quatro dias sem nova leitura, o Peek sinaliza a data dessa leitura ("Sem atualizações desde seg 12 out") e continua consultando a Aras. Encomendas costumam ficar quietas no fim de semana. Após três semanas sem novas leituras, o Peek mostra "Nada de novo há mais de três semanas. Se a encomenda ainda não chegou, fale com o vendedor." Veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Entregue, mas nada chegou.** Confira com vizinhos e moradores do endereço. O rastreador da Aras informa quem assinou pelo pacote — o Peek omite esse nome, portanto verifique diretamente no rastreador da Aras — e depois contate a loja e a Aras. Veja [entregue mas não recebido](guide:delivered-not-received).

**Perdida ou devolvida.** A loja contratou a entrega com a Aras, então procure a loja primeiro para reembolso ou reenvio. Veja [encomendas perdidas](guide:lost-parcel) e [devolvida ao remetente](guide:returned-to-sender).

## Atendimento ao cliente e contato da Aras Kargo

O Peek não conseguiu ler o site da Aras Kargo, portanto esta página não lista telefone nem e-mail: o Peek divulga apenas contatos verificados nas páginas da própria transportadora.

Em compras online, procure primeiro a loja que contratou o frete. No Peek, o local de cada movimentação indica a agência ou centro de transferência, e o local da entrega indica a agência que entregou: informe esse nome ao pedir ajuda.

## Rastreie uma encomenda Aras Kargo com o Peek

O [Peek](/) não pode ter certeza de que um código é da Aras Kargo apenas pelo formato: outras transportadoras também usam 13 dígitos. Cole mesmo assim: o Peek acompanha mais de 4.500 transportadoras.

- **13 dígitos começando por `60` ou `65`.** O Peek consulta a Aras Kargo de imediato junto a outras transportadoras compatíveis (Ukrposhta em alguns códigos iniciados por `60`, FedEx Poland em alguns iniciados por `65`). Enquanto consulta, exibe "Consultando serviços de rastreamento…". Se apenas a Aras tiver o pacote, mostra "Aras Kargo está com esta encomenda." e vincula a ela; se várias tiverem, pede que você escolha.
- **Outros 13 dígitos ou nenhuma transportadora com registro ainda.** Toque em "Trocar" e escolha Aras Kargo (digite Aras). O Peek passa a consultar diretamente o rastreador da Aras. Em um pacote já adicionado, use "Trocar transportadora".
- **Link do rastreador da Aras.** O Peek extrai o código e identifica Aras Kargo imediatamente ("Transportadora detectada").

Adicionado sem transportadora, o pacote exibe "Ainda sem histórico de rastreamento" enquanto o Peek consulta transportadoras e serviços de rastreamento. Se um serviço indicar apenas a Aras Kargo, com etapas datadas, o Peek vincula o envio à Aras.

**O que o Peek mostra.** O status atual, cada movimentação com hora e local (agência ou centro de transferência e província), os dias em que não houve entrega, redirecionamentos, o dia previsto de entrega e o horário da entrega com a agência. Mostra o nome do destinatário mascarado exatamente como a Aras envia, e omite nome do remetente, quem assinou, código de entrega, dados de pagamento e nota fiscal, volume e quantidade, barra de progresso e notas/mensagens. O Peek interpreta os horários da Aras no fuso da Turquia. O link "Abrir o site de Aras Kargo" na página do pacote abre o código diretamente no rastreador da Aras.

Quando o rastreador da Aras fica indisponível, o Peek consulta serviços de rastreamento e exibe o histórico deles nas palavras deles.

Enquanto o pacote estiver aberto, ou no dia da entrega com notificações ativadas, o Peek consulta a Aras até a cada 10 minutos; nos demais casos, e após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), consulta de hora em hora. Pode notificar em "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não pode contatar a Aras Kargo nem a loja por você.

## Perguntas sobre o rastreamento da Aras Kargo

### O que significa `TESLİMAT ŞUBESİNDE` no rastreamento da Aras Kargo?

Significa "na agência de entrega": a agência da Aras que leva o pacote até você já o recebeu. O status permanece igual após uma entrega não realizada — procure uma linha com motivo, exibida pelo Peek como "Tentativa de entrega".

### O que significa `YOLDA` na Aras Kargo?

Significa "a caminho": o pacote está viajando entre centros de transferência da Aras rumo à agência de entrega. O Peek exibe "Em trânsito".

### Como é um código de rastreamento da Aras Kargo?

São 13 dígitos, conforme o código do Peek. O Peek associa à Aras Kargo os que começam por `60` ou `65`, como `6048291736542`. Para outros códigos de 13 dígitos, selecione Aras Kargo você mesmo.

### Por que a Aras Kargo não encontra meu código de rastreamento?

`Girdiğiniz bilgiler için bir sonuç bulunamamıştır.` significa que a Aras ainda não tem registro para esse número. A loja pode ainda não ter entregue o pacote. Confira os 13 dígitos, aguarde um dia e fale com a loja.

### Como entro em contato com a Aras Kargo?

Pergunte primeiro à loja, que contratou o envio. O Peek não conseguiu ler o site da Aras Kargo, portanto esta página não traz telefone nem e-mail.

:::sources
- [Universal Parcel Scraper: Aras Kargo](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/aras-kargo) – nomes, códigos de 13 dígitos e formato `60`/`65`, rastreador e link, seções e textos em turco com etapas, mensagem de não encontrado, dia previsto de entrega, fuso turco e campos omitidos
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – outras transportadoras com o mesmo formato, ordem de consulta do Peek e serviços de rastreamento como alternativa
:::
