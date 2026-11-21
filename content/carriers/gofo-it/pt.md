---
title: Rastreio GOFO Italy: códigos GFIT, estados e reclamações
description: Segue uma encomenda GOFO em Itália: formato dos números GFIT e CIIT, significado dos estados em italiano, reclamação na AGCOM e seguimento no Peek.
slug: rastreio-gofo-italy
published: 2026-11-21
updated: 2026-11-21
---

A GOFO Italy é a rede italiana da GOFO, uma transportadora de encomendas presente em vários países, que faz entregas em Itália. A lista de transportadoras do Peek também a conhece como CIRRO Parcel Italy. Os seus códigos de seguimento começam por `GFIT` ou `CIIT`: segue a encomenda na página de rastreio italiana da GOFO, em gofo.com, ou cola o número na caixa acima. Ainda não tens o número? Vê [onde encontrar o número de seguimento](guide:find-tracking-number).

## Número de seguimento GOFO Italy: como é

Estas são as formas que o Peek associa à GOFO Italy, em maiúsculas ou minúsculas:

| Formato | Exemplo inventado |
| --- | --- |
| `GFIT` e 13 algarismos | `GFIT4829105736284` |
| `GFIT` e 14 algarismos (o último é dígito de controlo) | `GFIT48291057362848` |
| `CIIT` e 13 algarismos | `CIIT4829105736284` |
| `CIIT` e 14 algarismos | `CIIT48291057362841` |

As restantes redes da GOFO seguem o mesmo padrão, com o respetivo país depois de `GF` ou `CI`. Os números `GFFR` e `CIFR` pertencem à [GOFO France](carrier:gofo-fr), `GFNL` à GOFO Países Baixos e `GFES` à GOFO Espanha. Os `GFUS` de 14 algarismos, `GF` de 13 ou `CR` de 12 pertencem à [GOFO Express](carrier:gofo) nos Estados Unidos, uma rede distinta, e `GFCA` com 14 algarismos à GOFO Canada. Um código começado por `GFIT` com 12 ou 15 algarismos, ou um `GFIT` de 14 algarismos com o dígito de controlo errado, não é atribuído pelo Peek à GOFO Italy: confirma se há um erro de escrita. Mais sobre formatos em [formatos de números de seguimento](guide:tracking-number-formats).

## Estados de seguimento GOFO Italy: o que querem dizer em italiano

O Peek lê o seguimento italiano da GOFO, pelo que os registos aparecem nas palavras italianas originais seja qual for o teu idioma. Os mais comuns, pela ordem habitual:

| O que diz a GOFO | O que significa para ti |
| --- | --- |
| `Il pacco è in fase di preparazione da parte del mittente e ci sarà presto consegnato` | O remetente está a preparar a encomenda e vai entregá-la em breve à GOFO. |
| `Registrato presso il centro di trasferimento` | Registada num centro de transferência da GOFO. |
| `Il pacco ha lasciato il centro di smistamento` | Saiu de um centro de triagem da GOFO. |
| `Arrivato alla struttura di destinazione regionale GOFO` | Chegou à instalação regional da GOFO da tua área. |
| `Preparazione alla consegna` | Em preparação no depósito antes de o estafeta sair. |
| `Il corriere è fuori per la consegna` | Em distribuição com o estafeta. |
| `Consegna fallita, tipo:Destinatario assente` | Não entregue: não estava ninguém. |
| `Consegna fallita, tipo:L'indirizzo è errato` | Não entregue: morada incorreta. |
| `Il pacco è stato restituito al centro di smistamento` | Regressou ao centro de triagem da GOFO após entrega falhada. |
| `Consegnato` | Entregue. |

Duas linhas costumam baralhar. Enquanto nenhum outro registo se seguir ao primeiro, a GOFO ainda não tem a encomenda e o Peek mostra «Etiqueta criada». E `Il pacco è stato restituito al centro di smistamento` não quer dizer que voltou à loja: está de volta ao centro de triagem da GOFO, e o Peek mostra-o como «Problema reportado». Uma entrega surge apenas como `Consegnato`, em qualquer idioma: a linha da GOFO pode trazer mais detalhes sobre o destinatário, que o Peek deixa de fora.

O rastreio da GOFO agrupa também as encomendas em categorias gerais. `Avviso` assinala um problema marcado pela GOFO, mostrado pelo Peek como «Problema reportado»; por si só não implica uma tentativa de entrega. `Restituito` indica devolução ao remetente («Devolvida ao remetente»).

Se a [YunExpress](carrier:yunexpress) entregou a tua encomenda à GOFO Italy, o rastreio da YunExpress pode retransmitir registos da GOFO em inglês: `Preparing for delivery` no depósito antes de sair para entrega e `Package departed from sorting center` a par do italiano `Il pacco ha lasciato il centro di smistamento`.

## Encomenda GOFO atrasada, falhada, perdida ou danificada

**Sem novidades.** O Peek continua a consultar a GOFO. Se passarem quatro dias sem novos registos antes da saída para entrega, o Peek assinala-o («Sem atualizações há quatro dias») e inclui uma ligação para a página de ajuda da GOFO Italy («Pede ajuda a GOFO Italy»). Vê também [porque o seguimento não atualiza](guide:tracking-not-updating).

**Entrega falhada.** Após qualquer linha `Consegna fallita` sob o código de alerta da GOFO — seja `Destinatario assente`, `L'indirizzo è errato` ou outro motivo após `tipo:` — o Peek mostra «Tentativa de entrega». O passo seguinte cabe à GOFO: vê [entrega falhada](guide:missed-delivery) para saber o que verificar.

**Atrasada.** Segundo as regras europeias de defesa do consumidor, a loja tem de entregar em 30 dias salvo prazo diferente acordado. Se não cumprir, dá-lhe um prazo adicional razoável; se também esse passar, podes cancelar a compra e receber o reembolso total.

**Marcada `Consegnato`, mas não chegou.** A loja é responsável pela entrega: cabe-lhe contactar a transportadora e provar que a encomenda foi entregue; se não conseguir, tem de reenviar o artigo ou reembolsar-te. O que verificar primeiro à volta de casa: [entregue mas não recebida](guide:delivered-not-received).

**Danificada.** A loja responde pelos danos desde a expedição até receberes a encomenda, por isso verifica a caixa diante do estafeta sempre que possas. Se a embalagem tiver danos visíveis, a autoridade postal italiana AGCOM indica que podes recusá-la ou aceitá-la com reserva. Mais em [encomenda danificada](guide:damaged-parcel).

## Como reclamar sobre uma entrega da GOFO Italy

Começa pela loja que enviou a encomenda, pois responde pela entrega. Para reclamar contra a própria transportadora, a AGCOM, entidade reguladora italiana das comunicações e do setor postal, define um percurso para operadores postais — contando as encomendas até 31,5 kg como serviços postais:

1. Envia uma reclamação escrita ao operador através do formulário do site ou de um pedido por e-mail. Tem de responder em 45 dias.
2. Se a resposta não te satisfizer ou não chegar em 45 dias, pede conciliação ao operador, que termina no prazo de 60 dias.
3. Se também falhar, tens 90 dias para pedir à AGCOM a resolução do litígio com o Formulario CP. A AGCOM decide em 150 dias, sendo a decisão vinculativa para ambas as partes.

A AGCOM pode determinar reembolsos ou indemnizações regulamentares, mas não indemnizações por perdas e danos: para isso, e a qualquer momento, podes recorrer aos tribunais. Se o operador não responder à reclamação nem ao pedido de conciliação, denuncia-o no Portale delle segnalazioni da AGCOM. O centro de contacto da AGCOM atende de segunda a sexta-feira, das 10:00 às 14:00, pelo `800.185060` a partir de rede fixa italiana ou pelo `081.750750` a partir de telemóvel ou do estrangeiro.

## Apoio ao cliente GOFO Italy

A página de ajuda da GOFO Italy encontra-se no site italiano, gofo.com, e o Peek liga para ela através de «Pede ajuda a GOFO Italy» quando passam quatro dias sem novos registos antes da distribuição. A empresa responsável, GOFO Italia s.r.l., tem sede social na Via Juan Manuel Fangio 11, 20045 Lainate (MI). Tem à mão o código `GFIT` ou `CIIT` e o número da compra. A AGCOM recorda também que todos os operadores postais têm de disponibilizar canais de comunicação gratuitos aos utilizadores.

## Seguir uma encomenda GOFO Italy com o Peek

O [Peek](/) reconhece diretamente os códigos `GFIT` e `CIIT` (validando o dígito de controlo nos `GFIT` de 14 algarismos). Também podes colar uma ligação da página italiana de rastreio da GOFO (em gofo.com ou gofoexpress.it, com `id=` e o número).

Enquanto tens a encomenda aberta no ecrã ou no dia da entrega com as notificações ativas, e enquanto o seguimento da GOFO responde, o Peek verifica até de 10 em 10 minutos, e até de 2 em 2 minutos a partir das 6h (hora italiana) no dia em que a GOFO regista a saída para entrega; caso contrário, de hora a hora. Através de serviços de seguimento verifica de hora a hora, e até de 15 em 15 minutos no dia da distribuição enquanto a tens aberta ou com notificações ativas. Depois de dois dias sem novidades (12 horas enquanto ainda mostra «Etiqueta criada» ou «Aceite pela transportadora»), o Peek verifica de hora a hora.

O Peek mostra o estado, o histórico com o local indicado pela GOFO para cada registo, a hora de entrega e esses locais num mapa. Se a GOFO indicar apenas um código de depósito ou o país, o registo aparece sem local. As horas são lidas na hora italiana e mostradas no teu fuso horário; no peektracker.com e nas apps para iPhone e Android, uma linha no fim do histórico lembra-o («Horas no teu fuso horário: …»). Podes ativar notificações de «Todos os registos», «Passos importantes» ou «Só a entrega». Até a GOFO apresentar um primeiro registo, o Peek mostra «À espera de GOFO Italy» e continua a perguntar. Se dois dias depois a GOFO continuar sem reconhecer o número e nenhuma outra fonte tiver respondido, o Peek mostra «Ao fim de dois dias, a transportadora ainda não reconhece este número. Verifica-o, escolhe outra transportadora ou elimina a encomenda.» e verifica a partir daí uma vez por dia, e uma vez por semana passada uma semana desde que a adicionaste. Se a GOFO já registar o envio sem o ter lido ainda, o Peek mantém «À espera de GOFO Italy» sem pedir para confirmares o número. O Peek mostra a data prevista de entrega da GOFO até a encomenda ser entregue, devolvida ou ficar pronta para levantamento; não mostra o peso e deixa de fora as fotos de entrega e os avisos da GOFO. Não pode contactar a GOFO nem a loja por ti.

## Perguntas sobre o rastreio GOFO Italy

### Onde está a minha encomenda GOFO?

A última linha do seguimento indica-o. `Arrivato alla struttura di destinazione regionale GOFO` significa que está na instalação regional da tua área, `Preparazione alla consegna` que está a ser preparada para o estafeta e `Il corriere è fuori per la consegna` que segue com o estafeta. Se só aparecer a linha de preparação pelo remetente, a encomenda ainda não foi entregue à GOFO.

### A GOFO Italy é legítima?

A empresa italiana da GOFO é a GOFO Italia s.r.l., com sede em Lainate, na província de Milão. A autoridade postal AGCOM regista na Determina n. 12/26/DSP de 2 de setembro de 2026 que em 17 de junho de 2026 notificou a empresa de três alegadas infrações: exercer atividade postal em serviços do âmbito do serviço universal sem a licença individual exigida, sem carta de serviços (Carta dei servizi) e sem procedimentos de resolução de litígios para os utilizadores. Segundo a decisão, a empresa admitiu as acusações relativas à Carta dei servizi e aos procedimentos de litígio e pagou a coima em valor reduzido, levando a AGCOM a arquivar essa parte do processo; a empresa contestou a acusação sobre a autorização, seguindo essa parte a tramitação ordinária.

### GOFO e CIRRO Parcel são a mesma coisa?

Em Itália, para efeitos de rastreio, sim: a lista de transportadoras do Peek dá à GOFO Italy o nome alternativo CIRRO Parcel Italy. Um seguimento que diga apenas «Cirro» pode referir-se à rede italiana, francesa ou neerlandesa da GOFO, ou à linha internacional da Cirro que entrega as encomendas aos correios nacionais com os respetivos números.

### O que faço se a minha encomenda GOFO se perdeu?

Contacta a loja. Pelas regras da UE, a loja responde pela entrega até receberes a encomenda: se não conseguir provar a entrega, tem de reenviar o artigo ou reembolsar-te. Se a culpa for da transportadora, aplica-se a via de reclamação da AGCOM descrita acima. Mais em [encomenda perdida](guide:lost-parcel).

:::sources
- [AGCOM: Determina n. 12/26/DSP](https://www.agcom.it/sites/default/files/provvedimenti/determina/2026/Determina%20arch%20parziale%2012_26_DSP%20-%20Gofo%20pdf.pdf) – GOFO Italia s.r.l., sede em Lainate, processo AGCOM de 2026 e coima paga em valor reduzido (2 de setembro de 2026)
- [AGCOM: FAQ contenzioso tra utenti e operatori postali](https://www.agcom.it/faq-contenzioso-tra-utenti-e-operatori-postali) – reclamação 45 dias, conciliação 60 dias, Formulario CP em 90 dias, decisão em 150 dias, encomendas até 31,5 kg, danos, canais gratuitos, centro de contacto
- [Your Europe: Shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_en.htm) – entrega em 30 dias, prazo adicional, responsabilidade por danos até à receção
- [Your Europe: FAQs, shipping and delivery](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/faq/index_en.htm) – a loja prova a entrega, verificação na entrega
- [Universal Parcel Scraper: GOFO Italy](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/gofo-it) – formatos `GFIT`/`CIIT` com dígito de controlo `gofo-europe`, ligações, textos italianos e categorias, `Consegna fallita` sob o código `206`, CIRRO Parcel Italy, campos mostrados e descartados
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – outras redes GOFO, retransmissões em inglês da YunExpress, fim da data prevista (`GOFO_ENDED_STAGES`)
:::
