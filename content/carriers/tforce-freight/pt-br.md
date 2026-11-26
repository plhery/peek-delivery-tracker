---
title: Rastreamento TForce Freight: códigos PRO e status
description: Rastreie cargas da TForce Freight nos EUA ou Canadá: formato do código PRO, significado das etapas de rastreamento, entregas residenciais e reclamações.
slug: tforce-freight-tracking
published: 2026-11-26
updated: 2026-11-26
---

A TForce Freight é uma transportadora de carga fracionada (LTL, less-than-truckload): transporta paletes e cargas pesadas entre seus centros de serviço nos Estados Unidos e em todo o Canadá, além de mais de 600 localidades no México. Se um pedido grande ou outra carga está vindo pela TForce, acompanhe pelo código PRO informado pela loja ou empresa contratante: cole-o na caixa acima ou acesse a página Tracking em www.tforcefreight.com.

## Código de rastreamento TForce Freight: o número PRO

A página de rastreamento da TForce busca por código PRO, ou por conhecimento de embarque (BOL) ou número de pedido de compra (PO) combinado a um intervalo de datas de envio. O Peek rastreia pelo PRO. Com dígitos inventados:

| Código (inventado) | O que é |
| --- | --- |
| `482917363` | Código PRO: nove dígitos, sendo o último um dígito verificador (resto da divisão dos oito primeiros por 7, portanto sempre de 0 a 6). |
| `48291736` | Um PRO sem o zero inicial. O rastreador da TForce adiciona o `0`, e o Peek faz o mesmo depois que você escolhe TForce Freight. |
| `2205482917363` | Número PARS, usado por despachantes aduaneiros em cargas para o Canadá: `2205` seguido do PRO. Cole inteiro: o Peek consulta a TForce pelo PRO embutido. |

Um PRO de nove dígitos terminado em `7`, `8` ou `9` tem erro de digitação. O catálogo do Peek também reconhece a TForce Freight como UPS Freight e TForce.

**Onde encontrar.** No e-mail de envio da loja ou na página do pedido; em cargas entre empresas, peça ao remetente. Veja [como encontrar seu código de rastreamento](guide:find-tracking-number).

**Mesmo formato em outras transportadoras.** Códigos de nove dígitos com o mesmo dígito verificador também cabem em [TNT](carrier:tnt) e XPO. A TForce Logistics (TForce Final Mile), cujos códigos começam com `TF`, é uma transportadora separada no Peek. Se o e-mail indicar uma delas, escolha essa transportadora. Veja mais em [formatos de código de rastreamento](guide:tracking-number-formats).

## O que significam os status de rastreamento da TForce Freight

O rastreador da TForce exibe um status ao lado do PRO (como `In Transit` ou `Delivered`) seguido do histórico, da linha mais recente para a mais antiga, com cidade e estado do centro de serviço, data e hora. Como não há códigos numéricos, o Peek interpreta o texto. Na ordem habitual:

| A TForce Freight diz | O que significa para você | O Peek mostra |
| --- | --- | --- |
| `SHIPMENT HAS BEEN PICKED-UP.` | O motorista coletou a carga junto ao embarcador. | "Recebida pela transportadora" |
| `SHIPMENT IS AT A TFORCE FREIGHT LOCATION` | Está no centro de serviço do lado do remetente. | "Em trânsito" |
| `DEPARTURE` | Saiu de um centro de serviço. | "Em trânsito" |
| `SHIPMENT HAS ARRIVED AT A SERVICE CENTER.` | Chegou a um centro de serviço no trajeto ou ao centro que fará sua entrega. | "Em trânsito" |
| `SENT EMAIL. TFORCE FREIGHT IS ATTEMPTING TO OBTAIN APPOINTMENT INFORMATION` | A TForce enviou e-mail ao destinatário para agendar a entrega. Responda ao e-mail. | "Em trânsito" |
| `A DELIVERY APPOINTMENT HAS BEEN SCHEDULED WITH THE CONSIGNEE.` | Data de entrega combinada (às vezes registrada depois da entrega). | "Em trânsito" |
| `OUT FOR DELIVERY` | No caminhão para entrega hoje. | "Saiu para entrega" |
| `Shipment not delivered. Delivery attempted after normal business hours. Tforce will attempt delivery on next business day we service this area.` | Não entregue nesta rota. Toda linha iniciada por `Shipment not delivered.` segue esse padrão, com o motivo em seguida. | "Tentativa de entrega" |
| `SHIPMENT HAS BEEN DELIVERED TO THE CONSIGNEE.` | Entregue. A página da TForce informa quem assinou. | "Entregue" |

Um status `Delivered` junto ao PRO aparece sempre como "Entregue"; nos demais casos vale a linha mais recente compreendida pelo Peek, mantendo outras linhas no texto original da TForce. O site da TForce também envia alertas por e-mail. Veja [status de rastreamento explicados](guide:tracking-statuses).

## Como a TForce Freight realiza entregas

A TForce leva a carga até o centro de serviço da sua região e entrega de caminhão. Seu rastreador informa uma data prevista, exibida pelo Peek até a entrega ou o vencimento da data. O Peek não transfere o acompanhamento para outra transportadora.

A tarifa de regras da TForce (*LTL Rules Tariff UPGF 102-T*, vigente desde 17 de agosto de 2026) detalha o serviço:

- **Residências.** Onde os caminhões podem trafegar legalmente, a TForce precisa combinar antes a data e a janela aproximada com você ou o embarcador (negócios operados em casa contam como residência). A entrega ocorre na entrada térrea, entrada da garagem ou abrigo de carro, ou mediante retirada no terminal. Entrega residencial é serviço extra cobrado de quem contratou ou paga o frete.
- **Ao lado do caminhão.** Entrega padrão significa descarregar junto ao caminhão. Entrega interna (inside delivery) e plataforma elevatória (lift gate) custam à parte: confirme com quem contratou o frete se estão incluídas.
- **Aviso de chegada.** Quando os documentos pedem aviso, a TForce contata o destinatário até o próximo dia útil após a chegada ao centro de entrega, normalmente por telefone ou e-mail.
- **Não entregue.** Em caso de recusa ou destinatário não localizado, a TForce avisa o embarcador sobre armazenagem e taxas podem incidir. Nova tentativa é cobrada salvo se você retirar no centro de serviço (em residências, só se você perder a data agendada).

**Para o Canadá.** Segundo a página canadense da TForce, cargas dos EUA passam pela alfândega na fronteira por despachante aduaneiro. Se não forem liberadas ali, ficam em depósito alfandegado no destino com taxas de armazenagem, cabendo ao importador de registro pagar despachante, direitos e impostos.

## Carga TForce Freight não encontrada, atrasada, danificada ou extraviada

**Não encontrada.** Para um PRO desconhecido, o rastreador da TForce exibe `Not Available` e `Please check ProNumber` com o número. O Peek exibe "Aguardando TForce Freight" (se salva como TForce Freight) ou "Ainda sem histórico de rastreamento" e volta a consultar depois. Confira dígito por dígito e pergunte ao remetente se a carga já foi coletada.

**Dígito errado.** Um PRO cujo último dígito falha na verificação não pertence à TForce, e o Peek não a consulta. Se você escolher TForce Freight mesmo assim, o Peek avisa que ela "não emite códigos como este" e mostra "Confira o código de rastreamento".

**Sem atualização.** Após quatro dias sem nova movimentação em "Recebida pela transportadora" ou "Em trânsito", o Peek exibe "Sem atualizações desde seg 12 out", o texto "É comum uma encomenda ficar sem atualizações em voos longos, na alfândega ou no fim de semana. O Peek continua consultando TForce Freight." e o link "Pedir ajuda a TForce Freight". Fale com a loja ou embarcador; veja [por que o rastreamento para de atualizar](guide:tracking-not-updating).

**Tentativa perdida.** Após uma linha `Shipment not delivered.`, o Peek sinaliza "Confira os próximos passos da transportadora". Ligue para a TForce ou peça a quem contratou o frete para reagendar. Veja [entregas perdidas](guide:missed-delivery).

**Falta ou dano na entrega.** A página Cargo Claims Information da TForce orienta conferir tudo na hora e avisar o motorista imediatamente:

- Conte os volumes comparando com o recibo de entrega e abra caixas danificadas junto ao motorista.
- Descreva com precisão o dano ou falta tanto na sua via quanto na via da TForce.
- Não recuse a carga a menos que o dano tenha inutilizado totalmente os bens.
- Peça ao responsável de Over, Short & Damage (OS&D) do centro de serviço local para rastrear volumes faltantes.

**Dano descoberto depois.** Comunique em até **5 dias úteis** da entrega e solicite vistoria, mantendo a embalagem como chegou (o laudo de vistoria não substitui a reclamação formal).

**Abrir reclamação.** A reclamação é um pedido formal por escrito feito pelo dono da mercadoria; em compras on-line, comece pela loja contratante. Regras da TForce:

- Envie em até **nove meses** da entrega (ou da data de expedição se nada chegou).
- Use o Standard Claim Form da TForce ou carta indicando a remessa e o valor, acompanhados da fatura do fornecedor, conta do frete, conhecimento (BOL) e eventuais orçamentos de reparo, por correio ou fax (abaixo), nunca ambos.
- A TForce confirma o recebimento em até **30 dias**. Sua responsabilidade limita-se a US$ 25,00 por libra por volume (menos para certos itens), salvo contratação de seguro adicional no BOL.

Se constar entregue sem você ter recebido, veja [entregue mas não recebida](guide:delivered-not-received). Mais em [encomenda danificada](guide:damaged-parcel) e [encomenda extraviada](guide:lost-parcel).

## Atendimento e contato da TForce Freight

Canais publicados nas páginas da TForce:

- **Atendimento ao cliente:** `(800) 333-7400`, ligação gratuita nos EUA, Canadá, Guam, Porto Rico e Ilhas Virgens Americanas para rastreamento, comprovante e problemas; de outros países, `(804) 231-8555`.
- **E-mail:** formulário Email TForce Freight Customer Support, acessível pela página Contact Us da TForce, com campo para o PRO.
- **Departamento de sinistros:** `(888) 653-9720` para status de reclamações. Envie pedidos para `Claims Processing, TForce Freight, PO Box 1216, Richmond, VA 23218`, ou por fax para `(866) 580-1944`.
- **Seu centro de serviço:** no Service Center Directory, em Service Information no site www.tforcefreight.com.

A loja ou empresa remetente costuma resolver mais rápido por ser a contratante direta.

Todas as páginas da TForce citadas aqui estão em www.tforcefreight.com: Tracking, Fact Sheet, Rules Tariffs (com LTL Rules Tariff UPGF 102-T), Canada, Cargo Claims Information, Contact Us (com Email TForce Freight Customer Support) e Service Information.

## Acompanhe uma carga TForce Freight com o Peek

O formato de nove dígitos não identifica a TForce sozinho, pois também cabe em TNT e XPO. Cole o código assim mesmo: o Peek acompanha mais de 4.500 transportadoras. Enquanto exibe "Consultando serviços de rastreamento…", consulta as três ao mesmo tempo — e depois DPD Ireland e serviços de rastreamento se nenhuma responder; se apenas a TForce tiver o envio, aparece "TForce Freight está com esta encomenda.", e se várias o reconhecerem você escolhe. Para códigos PARS, consulta TForce junto com FAN Courier; para PRO de oito dígitos, consulta apenas serviços de rastreamento. Você também pode clicar em "Trocar" e escolher TForce Freight em "Compatível com este código", ou usar "Trocar transportadora" depois. Links do rastreador da TForce (`proNumbers=`) ou da UPS Freight (`TrackProNumber=`) apontam direto para a TForce ("Transportadora detectada").

O Peek consulta o rastreador da TForce somente com o PRO, exibindo status, cada linha com cidade e estado, data prevista, país de destino no mapa, peso na sua unidade preferida e, visíveis apenas para você, a cidade do destinatário no mapa e o nome em "Destinatário". Embarcador, agendamento, números BOL e PO e contagem de volumes são omitidos, e não há comprovante de entrega. "Abrir o site de TForce Freight" abre o código direto no rastreador da TForce (ou o PRO embutido em um PARS). Se o site ficar fora do ar ou exigir CAPTCHA, o Peek recorre a [serviços de rastreamento](guide:universal-tracker).

A TForce informa cada etapa na hora local do centro de serviço, sem fuso: o Peek interpreta pelo fuso da cidade (ou do estado/província, linha vizinha, destino ou horário do Leste como padrão) e exibe tudo no fuso do seu aparelho.

Com a carga aberta no Peek, ou no dia da entrega com notificações ligadas, o Peek consulta a TForce diretamente até a cada 10 minutos, e até a cada 2 minutos a partir das 6h no relógio da última movimentação (horário do Leste por padrão) no dia em que sair para entrega. Após dois dias sem novidades (12 horas em "Etiqueta criada" ou "Recebida pela transportadora"), verifica de hora em hora. Você pode escolher notificações para "Cada movimentação", "Etapas importantes" ou "Só a entrega". O Peek não contata a TForce, a loja ou o embarcador nem abre reclamações por você.

## Perguntas sobre o rastreamento da TForce Freight

### O que é um código PRO da TForce Freight?

É o código de rastreamento da TForce: nove dígitos, com um dígito verificador final de 0 a 6, fornecido pela loja ou empresa contratante do frete.

### Posso rastrear a TForce Freight sem código PRO?

Na página da TForce, sim: pelo conhecimento (BOL) ou número de pedido (PO) com um intervalo de datas de envio. O Peek rastreia apenas pelo PRO.

### A TForce Freight entrega em residências?

Sim, onde seus caminhões puderem circular legalmente, com cobrança adicional. A TForce combina antes data e janela aproximada com você ou o embarcador e entrega na entrada térrea, entrada da garagem ou abrigo de carro; entrega interna e plataforma elevatória também custam extra.

### Qual é o prazo para abrir reclamação na TForce Freight?

Nove meses a partir da entrega (ou da data de expedição se nada chegou). Danos percebidos após a entrega devem ser informados em até 5 dias úteis.

### Qual é o telefone de atendimento da TForce Freight?

`(800) 333-7400`, ligação gratuita nos EUA e Canadá. De outros países, `(804) 231-8555`.

:::sources
- [Universal Parcel Scraper: TForce Freight](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/tforce-freight) – names, PRO shapes, links, wordings and stages, not-found answer, what Peek keeps, clocks
- [Universal Parcel Scraper](https://github.com/plhery/universal-parcel-scraper) – carriers sharing the shape, no hand-off, tracking services as a fallback
:::
