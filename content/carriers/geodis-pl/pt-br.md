---
title: Rastreamento GEODIS Poland: código, status e entregas
description: Rastreie uma remessa da GEODIS Poland (antiga Pekaes): formato do código PL, significado dos status, entregas, retirada em terminal e contato.
slug: rastreamento-geodis-polonia
published: 2026-11-24
updated: 2026-11-24
---

A GEODIS Poland é a rede polonesa de paletes e encomendas da GEODIS, grupo francês de transportes controlado pela SNCF. A GEODIS comprou a PEKAES em 2021, e hoje a rede é operada pela GEODIS Road Network, sediada em Błonie. O caminho para rastrear depende do que o remetente forneceu:

- **Um código de `PL` e 16 dígitos, ou um link da Strefa Klienta:** trata-se de um envio da GEODIS Poland (pacote ou palete). Cole na caixa acima ou pesquise no rastreador da GEODIS Poland, [Strefa Klienta](https://strefaklienta.pekaes.geodis.pl/en/).
- **Outro código GEODIS:** o Peek lê outro rastreador da GEODIS, o da GEODIS France, cujos códigos começam com `1G` (abaixo). A busca em geodis.com localiza envios aéreos, marítimos e de última milha, direcionando cargas rodoviárias à Strefa Klienta.
- **Sem número:** a loja ou o remetente informa o código, geralmente na confirmação do pedido ou no e-mail de envio.

## Código de rastreamento GEODIS Poland: formato e onde achar

A GEODIS atende apenas empresas, portanto o número fica com quem enviou a mercadoria — geralmente uma loja ou fabricante; se não o tiver, peça ao remetente. Ainda sem código? Veja [como encontrar o código de rastreamento](guide:find-tracking-number).

| Código (inventado) | O que é |
| --- | --- |
| `PL4829105736491827` | `PL` e 16 dígitos: código de remessa da GEODIS Poland, chamado de "Shipment No." no rastreador. |
| `1G4829105736` | `1G` e 10 letras maiúsculas ou dígitos: código da GEODIS na França, acompanhado na área francesa de destinatários, não na Strefa Klienta. |

Na Strefa Klienta, digite o código em "Enter Shipment No." e clique em "Search". Se responder `Shipment with such number does not exist`, o sistema não reconhece o número: fale com a loja ou remetente, como orienta a GEODIS. A mensagem em polonês `Proszę wprowadzić właściwy numer przesyłki.` pede um número válido: verifique se há erro de digitação.

## O que significam os status da GEODIS Poland

A Strefa Klienta funciona em polonês e inglês. A busca mostra primeiro um resumo com status, país de destino, terminais, datas, volumes e código postal e cidade de entrega; o link "Details" abre cada movimentação com horário e local do terminal. Os textos em inglês, na ordem usual:

| A GEODIS mostra | O que significa para você |
| --- | --- |
| `Received from the sender` | A GEODIS coletou a remessa com o remetente. |
| `Inbound Scan From Driver to Terminal` | Um motorista trouxe a remessa ao terminal — o de coleta após recolher ou, após `Outbound for Delivery`, o terminal de entrega ao fim da rota. Sem `Consignee Absent` ou `Failed delivery (SMS)` antes, aquela rota não chegou a tentar entregar e a remessa aguarda a próxima. |
| `Outbound Scan from (Collection) Terminal to Line-haul` | Saiu daquele terminal em caminhão rumo ao terminal que atende seu endereço. |
| `Inbound Scan from Line-haul to (Delivery) Terminal` | Chegou ao terminal responsável pela entrega. |
| `Awaiting Delivery` | Aguardando naquele terminal pela saída de uma rota de entrega. |
| `Outbound for Delivery` | No caminhão de entrega. |
| `Consignee Absent` | O motorista não encontrou você no local. |
| `Failed delivery (SMS)` | Tentativa de entrega sem sucesso, confirmada por SMS. |
| `Awaiting designation of new delivery date` | A GEODIS aguarda a definição de nova data de entrega. |
| `Awaiting Decision` | A GEODIS aguarda instruções do remetente. |
| `Delivery Refusal` | O destinatário recusou a remessa. |
| `Request for Return` | A GEODIS recebeu ordem de devolução: está a caminho de volta ao remetente, ainda não chegou lá. |
| `Delivered` | Entregue. `Delivered (SMS)` significa o mesmo, confirmado por SMS. |
| `Claim Received` | Foi aberta uma reclamação; é apenas um registro e não move a carga. |

Alguns eventos indicam retenção: `Stopped at Hub` e `Stopped at Collection Terminal` (retida no hub de triagem ou no terminal inicial), `Not Dispatched` (perdeu o caminhão ou rota prevista, mas segue na rede) e `Failure`. Uma linha começando por `Pseudoscan` cancela os eventos anteriores de entrega, devolução ou reclamação. A página em polonês exibe etapas equivalentes, como `wydanie do dostawy` (saiu para entrega) e `doręczone` (entregue). Mais sobre etapas em [status de rastreamento](guide:tracking-statuses).

## Entregas da GEODIS na Polônia: domicílio, aviso e retirada

As condições gerais da GEODIS, vigentes desde 4 de novembro de 2024, regem as entregas. Elas vinculam o cliente contratante (geralmente a loja) e detalham principalmente entregas de carga paletizada (fracionada):

- **Entrega perdida.** Se não houver ninguém, o motorista deixa aviso da tentativa. Após a segunda tentativa sem sucesso, a remessa volta ao remetente; também volta se você recusar ou se o endereço informado estiver errado. Veja [entrega perdida](guide:missed-delivery) e [devolvida ao remetente](guide:returned-to-sender).
- **Paletes em endereço residencial.** A GEODIS entrega apenas em dias úteis, de segunda a sexta-feira, das 8h às 16h. O cliente precisa fornecer seu celular: a GEODIS envia atualizações e agenda previamente por telefone, SMS ou e-mail, ligando no mais tardar no dia disponível para entrega. Se não conseguir contato em dois dias úteis, avisa o contratante e devolve ao remetente.
- **Descarga.** Em endereços residenciais, o motorista descarrega o palete no nível do solo ou no ponto pavimentado e seguro mais próximo se o caminhão não puder estacionar com segurança. Subir ou levar para dentro ("wniesienie"), horário marcado e entrega até as 10h ou 12h (apenas alguns códigos postais) são serviços extras contratados pelo remetente.
- **Retirada no terminal.** Quando o remetente autoriza retirada em um terminal da GEODIS, a carga aguarda 48 horas em dias úteis a partir da instrução, com retirada de segunda a sexta-feira das 11h às 16h; depois volta ao remetente.
- **Pagamento na entrega.** Aceita dinheiro, cartão, BLIK ou outro meio sem dinheiro admitido pela GEODIS, até 10.000 PLN.
- **Assinatura.** Você assina no aparelho do motorista ou nos documentos de entrega. Os termos da GEODIS proíbem conferir o conteúdo antes de assinar, então inspecione a embalagem e anote qualquer avaria ou falta nos documentos nesse momento.

## Remessa GEODIS Poland atrasada, avariada ou não entregue

**Sem atualização.** Se ficar dias parada, veja [rastreamento que não atualiza](guide:tracking-not-updating) e fale com a loja.

**Atrasada.** A autoridade polonesa do consumidor, UOKiK, determina que o vendedor deve entregar sem demora e em até 30 dias da compra, salvo acordo diverso. Passado o prazo, você pode conceder prazo extra e, se ainda não chegar, rescindir o contrato.

**Avaria ou falta de itens.** Lavre termo de avaria ("protokół szkody") com o motorista no ato da entrega, no formulário da GEODIS, assinado por ambos. Danos ocultos ou faltas devem ser reportados à GEODIS em até 7 dias do recebimento, cabendo ao reclamante provar que ocorreram antes da entrega. Guarde a mercadoria para vistoria e tire fotos com data e hora. O formulário alerta que o termo de avaria sozinho não substitui o pedido de reclamação. Veja [encomenda avariada](guide:damaged-parcel).

**Quem reclama.** Pelos termos da GEODIS, reclamações partem do cliente contratante ou de quem ele autorizar por escrito, enviadas ao departamento de reclamações em Błonie: em até 14 dias do recebimento para avaria ou falta, e até 21 dias da data prevista para atraso ou extravio. Como comprador, trate com a loja: segundo a UOKiK, o vendedor responde pelo envio até você recebê-lo — mesmo se a transportadora causou o dano —, então você reclama com a loja, que aciona a transportadora.

## Contato e atendimento da GEODIS Poland

Canais da GEODIS na Polônia:

- **Atendimento ao cliente (Dział Obsługi Klienta):** +48 22 460 26 26, listado na página polonesa da Strefa Klienta, sem horários publicados.
- **Reclamações por correio:** Dział Reklamacji, ul. Spedycyjna 1, 05-870 Błonie. Os formulários de reclamação e avaria ficam na Strefa Klienta.
- **Unidades:** a [página de contato polonesa da GEODIS](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) lista 42 endereços na Polônia, incluindo filiais da GEODIS Road Network.
- **Formulário online:** o formulário em geodis.com é voltado a cotações e exige nome de empresa.

## Como rastrear uma remessa da GEODIS Poland no Peek

`PL` seguido de 16 dígitos não é exclusivo da GEODIS (`PL` indica Polônia), portanto o Peek não fixa a GEODIS Poland só pelo código. Cole mesmo assim: o Peek acompanha mais de 4.500 transportadoras. Ele consulta a GEODIS Poland e mostra "GEODIS Poland está com esta encomenda" quando ela reconhece o número. Se ainda não constar, aparece "Provavelmente GEODIS Poland" e "Aguardando a primeira movimentação de GEODIS Poland" enquanto o Peek segue consultando; se após uma semana continuar ausente, sugere conferir com o remetente ou escolher outra transportadora. Para definir manualmente, toque em "Trocar" e selecione "GEODIS Poland" em "Compatível com este código" ou pesquise (digitar Pekaes também encontra; "GEODIS" sozinho é a GEODIS France). Em remessas já adicionadas, use "Trocar transportadora". Links da Strefa Klienta contendo o número identificam a GEODIS Poland de imediato.

O Peek lê a Strefa Klienta em inglês e exibe o status, cada evento nas palavras da GEODIS com a cidade do terminal, o horário da entrega e o país de destino. Leituras em terminais poloneses usam o horário de Varsóvia; terminais no exterior usam o relógio local. Todos aparecem no fuso do seu aparelho; em peektracker.com e nos aplicativos para iPhone e Android, o histórico termina com "Horários no seu fuso horário:" e o nome dele. Quando uma linha `Pseudoscan` cancela eventos anteriores, o Peek também os descarta, e `Claim Received` mantém o status anterior. O rastreador da GEODIS não fornece data prevista, peso, serviço, nome do destinatário nem comprovante; o Peek omite CEP, endereço, quantidade de volumes, códigos de terminal e observações (que podem citar quem assinou).

Enquanto a remessa estiver aberta, ou no dia da entrega com alertas ligados, o Peek verifica até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no horário local no dia em que a GEODIS registra saída para entrega, enquanto o rastreador da GEODIS responder (por serviços de rastreamento, até a cada 15 minutos enquanto o pacote estiver aberto ou com alertas ligados); fora disso, de hora em hora. Após dois dias sem novidade (12 horas enquanto ainda mostra "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Ele pode avisar em "Cada movimentação", "Etapas importantes" ou "Só a entrega", mas não pode marcar data, combinar retirada nem abrir reclamação: ligue para a GEODIS ou fale com a loja.

## Perguntas sobre o rastreamento GEODIS Poland

### A GEODIS Poland é a mesma empresa que a Pekaes?

Sim. A GEODIS adquiriu a PEKAES em 2021 e hoje a rede é operada pela GEODIS Road Network. O rastreador continua em strefaklienta.pekaes.geodis.pl.

### A GEODIS entrega aos sábados na Polônia?

Os termos da GEODIS não citam entregas aos sábados. Eles fixam entregas de paletes em endereços residenciais em dias úteis, de segunda a sexta-feira das 8h às 16h, e contam os prazos de encomendas excluindo sábados, domingos e feriados.

### O motorista da GEODIS leva o pedido para dentro de casa?

Somente em remessas paletizadas e apenas se o contratante pediu o serviço de movimentação interna (até 300 kg, até 150 m do ponto de descarga e até o segundo andar sem elevador). Nos demais casos, a descarga é feita no térreo do endereço.

### Quanto tempo a GEODIS leva para entregar na Polônia?

A GEODIS informa 24 a 48 horas dentro da Polônia no padrão, ou dia seguinte antes do meio-dia no expresso. Seus termos preveem 48 horas após a coleta para encomendas até 30 kg (excluindo fins de semana e feriados) e 2 dias úteis para paletes, ambos "em regra".

:::sources
- [GEODIS Poland: Strefa Klienta, shipment tracking](https://strefaklienta.pekaes.geodis.pl/en/) – campo de busca e botão, resposta de não encontrado, formulários de reclamação e avaria
- [GEODIS Poland: Strefa Klienta, Śledzenie przesyłek](https://strefaklienta.pekaes.geodis.pl/pl/) – telefone de atendimento e termos gerais
- [GEODIS Road Network: Ogólny Regulamin Świadczenia Usług, a partir de 4 de novembro de 2024](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GEODIS%20RN_Ogolny%20Regulamin%20Swiadczenia%20Uslug_od%2004.11.2024.pdf) – contratante, tentativas, paletes residenciais, aviso, descarga, movimentação interna, retirada em terminal, pagamento na entrega, assinatura, prazos e reclamações
- [GEODIS Road Network: Formularz zgłoszenia reklamacji](https://strefaklienta.pekaes.geodis.pl/pl/?file=files/pekaes/Dla%20Klienta%20-%20nowe%20www/GEODIS_SK/GRN%20Formularz%20zgloszenia%20reklamacji.pdf) – endereço de reclamações e aviso de que termo de avaria não substitui reclamação
- [GEODIS: Śledź swoją przesyłkę](https://geodis.com/pl-pl/sledz-swoja-przesylke) – onde achar o código, aéreo/marítimo/última milha em geodis.com, rodoviário no rastreador próprio e código inválido
- [GEODIS: Twój obszar roboczy](https://geodis.com/pl-pl/twoj-obszar-roboczy) – Strefa Klienta como rastreador da GEODIS
- [GEODIS: Transport palet i paczek](https://geodis.com/pl-pl/uslugi-transportowe/transport-palet-i-paczek) – atendimento corporativo e prazos
- [GEODIS: Skontaktuj się z nami](https://geodis.com/pl-pl/skontaktuj-sie-z-nami) – formulário de contato e 42 endereços
- [GEODIS: Historia](https://geodis.com/pl-pl/historia) – compra da PEKAES em 2021 e SNCF
- [UOKiK: Odpowiedzialność za przesyłkę](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/odpowiedzialnosc-sprzedawcy-za-przesylke/) – vendedor responsável até a entrega
- [UOKiK: Wydanie towaru](https://prawakonsumenta.uokik.gov.pl/prawa-ogolne/wydanie-towaru/) – 30 dias, prazo adicional e rescisão
:::
