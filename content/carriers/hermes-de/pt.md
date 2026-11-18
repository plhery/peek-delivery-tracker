---
title: Rastreamento Hermes Alemanha: estados explicados e entrega
description: Segue uma encomenda Hermes Germany (myHermes) em português: formatos do número de seguimento, estados traduzidos, entrega em Portugal, alfândega e Peek.
slug: rastreamento-hermes
published: 2026-11-18
updated: 2026-11-18
---

A Hermes Germany (também apresentada como *myHermes* ou *Hermes Borderguru*) é a transportadora alemã de encomendas do grupo Otto (*Otto Group*). Para seguires uma compra expedida da Alemanha, podes consultar o site alemão `myhermes.de` ou colar o código na caixa acima no [Peek](/) para leres a etapa atual em português e as horas no teu fuso horário.

No Reino Unido, a antiga Hermes UK mudou para [Evri](carrier:evri) em março de 2022, mantendo-se a Hermes Germany como transportadora alemã independente. Para mobiliário e grandes eletrodomésticos entregues na Alemanha por equipas de duas pessoas existe um serviço separado (*Hermes Einrichtungs-Service*).

## Número de seguimento Hermes: onde encontrar e formatos

O número de envio (*Sendungsnummer*) vem no e-mail de expedição da loja ou nos detalhes da encomenda (por exemplo em *Mein Konto* na OTTO). Se não o tens, pede-o ao vendedor ou vê [como encontrar o número de seguimento](guide:find-tracking-number).

O seguimento público da Hermes Germany aceita códigos alfanuméricos de 8 a 20 caracteres com pelo menos um algarismo:

| Formato (inventado) | O que identifica | Reconhecimento no Peek |
| --- | --- | --- |
| `H1048291057364928` | `H` seguido de 16 a 19 algarismos: exclusivo da Hermes Germany. | Reconhecido de imediato como Hermes Germany. |
| `74829105736493` | 14 algarismos com controlo Hermes: partilhado com [DPD](carrier:dpd-de), [BRT](carrier:brt), [Chronopost](carrier:chronopost) e outras redes. Começando por `022` (`02248291057367`), a Hermes é testada primeiro. | Sugerido como "Provavelmente Hermes Germany"; um código de 15 algarismos com zero extra à esquerda (`002248291057367`) é validado da mesma forma. |
| `H482910573649283` | `H` seguido de 15 algarismos: Hermes Germany ou [Evri](carrier:evri) (Reino Unido). | O Peek consulta a Hermes Germany (e a Evri se o último algarismo validar o controlo Evri). |

O serviço de bagagem *Hermes Gepäckversand* (Deutsche Bahn) e referências de 14 algarismos fora do controlo do código de barras pesquisam-se pelo número de encomenda: carrega em "Alterar" (ou "Alterar transportadora") e escolhe **Hermes Germany**.

## Estados de rastreamento da Hermes explicados em português

O portal `myhermes.de` está apenas em alemão; em português, o Peek pede à Hermes Germany as frases oficiais em inglês, embora algumas linhas permaneçam em alemão:

| Mensagem da Hermes | Etapa no Peek | O que significa |
| --- | --- | --- |
| `The shipment was announced to Hermes electronically. Further information will follow as soon as Hermes received the shipment.` | "Etiqueta criada" | Dados enviados pelo remetente; a Hermes ainda não recebeu fisicamente o volume. |
| `The shipment was taken over by Hermes in … and is prepared for further shipping.` | "Aceite pela transportadora" | Volume recolhido no centro alemão indicado. |
| `Die Sendung wird an Hermes weitergeleitet. Weitere Informationen folgen, sobald uns die Sendung erreicht hat.` | "Em trânsito" | Envio internacional a caminho da rede Hermes. |
| `The shipment has arrived in the destination country … and is prepared for the further shipping.` | "Em trânsito" | Chegou ao país de destino e segue em triagem para o parceiro local. |
| `The shipment was prepared for further shipping (…).` | "Em trânsito" | Triagem feita pelo parceiro no país indicado entre parênteses. |
| `The shipment was loaded into the delivery vehicle and is expected to be delivered today.` / `The shipment was loaded into the delivery vehicle. The delivery is expected to take place today.` | "Em distribuição" | Na carrinha para entrega prevista hoje. |
| `The shipment is ready for pick up in a parcel shop/store.` | "Pronta para levantamento" | Disponível num ponto de recolha ou *PaketShop*. |
| `The shipment could not be delivered.` / `The shipment could not be delivered because the address was not found.` | "Tentativa de entrega" | Entrega não conseguida ou morada não encontrada (`Die Zustelladresse wurde nicht gefunden. Mögliche Gründe: Fehler in der Adressübermittlung. Bitte PaketShop buchen oder Kundenservice kontaktieren.`). |
| `The shipment was not picked up in the parcel shop/store.` | "Problema reportado" | Prazo de recolha no ponto expirado; regressa ao remetente. |
| `The shipment was delivered to the recipient address.` / `Die Sendung wurde erfolgreich zugestellt.` | "Entregue" | Entregue na morada indicada ou pelo parceiro internacional. |

As linhas `Für die Sendung wurde ein WunschPaketShop gebucht.` e `Ein WunschTag für die Zustellung wurde gebucht.` registam apenas uma preferência marcada, não um movimento: o Peek ignora-as. Se a volta ficar sem tempo antes da tua morada, não há falha registada e o volume mantém-se "Em trânsito" para o dia útil seguinte.

## Entrega na Alemanha e envios para Portugal

**Na Alemanha.** A rede soma mais de 17 000 *Hermes PaketShops*. Nos artigos em stock enviados pela OTTO via *Hermes Paketversand*, a entrega decorre normalmente em 1 a 2 dias úteis (segunda a sábado). Com a *Hermes WunschZustellung* (seguimento ou app), o destinatário escolhe local seguro (*Ablageort*), vizinho (*Nachbar*), *PaketShop* ou outro dia (*Tag*). Num *PaketShop*, a guarda vai até 7 dias úteis mediante identificação (*Personalausweis*). Nos cacifos *Hermes Box*, pede-se um código no seguimento com 72 horas para retirar (mostrado como "Pronta para levantamento" até à recolha). Entregas volumosas por equipas de dois (*2-Mann-Teams*) têm aviso por e-mail/SMS, chamada 30 a 60 minutos antes e *Live-Tracking* no mapa à meia hora.

**Da Alemanha para Portugal.** Fora da Alemanha, a Hermes entrega o último troço a um parceiro local. Quando a Hermes inclui a hiperligação do parceiro internacional e o Peek reconhece o domínio e o código, a ficha mostra "Entrega: …" (ou "Anunciada a …, ainda não recebida" antes da primeira leitura própria) e acompanha as leituras locais. Pontos de recolha e prazos de guarda em Portugal seguem as regras dessa transportadora.

## Alfândega, IVA e burlas por SMS

- **Alemanha para Portugal (UE):** segundo a Autoridade Tributária e Aduaneira, encomendas expedidas de outro Estado-Membro da União Europeia não têm formalidades de desalfandegamento.
- **Fora da UE:** nas vendas à distância extra-UE, a reforma aduaneira europeia acaba com a isenção até 150 €: a partir de **1 de julho de 2026** aplica-se um direito fixo de **3 €** por categoria pautal de artigos (até 150 €), acrescido desde **1 de novembro de 2026** de uma taxa europeia de tratamento de **2 €** por categoria de artigos independentemente do valor.

> **Atenção ao phishing:** uma encomenda vinda da Alemanha para Portugal não paga alfândega. Em Portugal, os [CTT](carrier:ctt) lembram que só envios extra-UE recebem aviso de desalfandegamento (prazo de 20 dias no Portal de Desalfandegamento) e que as hiperligações genuínas começam por `www.ctt.pt`.

## Encomenda Hermes atrasada, extraviada ou danificada

- **Parada em "Etiqueta criada":** a Hermes ainda aguarda a entrega física pelo vendedor; contacta a loja e lê [porque é que o seguimento deixa de atualizar](guide:tracking-not-updating).
- **Dada como entregue mas não chegou:** verifica vizinhos e ponto de recolha e avisa a loja; consulta [entregue mas não recebida](guide:delivered-not-received).
- **Compras online (UE e Alemanha):** o vendedor profissional responde pelo transporte até à entrega física (§ 475 BGB) no prazo de **30 dias**, salvo acordo distinto, e tens **14 dias** após a receção para livre resolução (§§ 355 e 356 BGB).
- **Direito comercial alemão e conciliação:** pelo § 438 HGB, danos visíveis comunicam-se na entrega, danos ocultos por escrito em **7 dias** e atraso em **21 dias** (direitos do destinatário no § 421 HGB). Sem acordo após reclamação escrita à Hermes por perda, furto ou dano de envio sem condições especiais (litígio de 10 € a 2 500 €), remetente ou destinatário pode recorrer gratuitamente em alemão à **Schlichtungsstelle Post** (*Bundesnetzagentur*), obrigatória para o operador perante consumidores.

## Seguir uma encomenda Hermes Germany com o Peek

O [Peek](/) consulta o serviço de destinatários da Hermes sem código postal e aceita hiperligações `myhermes.de`. Em códigos partilhados de 14 algarismos mostra "À espera do primeiro registo de Hermes Germany" até à primeira leitura. Extrai do texto o centro alemão, região, país ou localidade do *PaketShop* para o mapa, converte cada hora ("Horas no teu fuso horário: …") e lê apenas a morada do *PaketShop* ativo ou utilizado, nunca a morada privada.

Com a encomenda aberta no ecrã, ou no dia da entrega com notificações ativas, o Peek verifica até a cada 10 minutos — e a cada 2 minutos no dia de saída para distribuição desde as 6h00 alemãs (ou meia hora antes da janela Hermes) — enquanto a Hermes responder diretamente (por serviços de seguimento: até 15 minutos nesse dia, de hora a hora no resto); caso contrário, verifica de hora a hora, mesmo após dois dias sem leituras (ou 12 horas em "Etiqueta criada" ou "Aceite pela transportadora"). Com parceiro local em Portugal, segue o ritmo desse operador. Alertas: "Todas as leituras", "Passos importantes" ou "Só a entrega"; números antigos apagados devolvem 404.

## Perguntas frequentes (FAQ)

### A Hermes e a Evri são a mesma transportadora?

Apenas no Reino Unido, onde a Hermes UK passou a [Evri](carrier:evri) em março de 2022. A Hermes Germany é uma empresa alemã distinta do grupo Otto; em códigos `H` mais 15 algarismos o Peek testa ambas se o controlo final coincidir.

### Porque é que as leituras aparecem em inglês ou em alemão?

O serviço da Hermes Germany só disponibiliza os textos dos eventos em alemão e em inglês. Para português, o Peek pede o inglês (algumas linhas de parceiros ficam em alemão) e traduz a etapa principal para português.

### Como seguir móveis enviados pela Hermes na Alemanha?

Entregas por equipas de dois operadores pertencem à *Hermes Einrichtungs-Service*, separada da *myHermes* (aviso por e-mail/SMS e chamada 30 a 60 minutos antes). Escolhe-a em "Alterar transportadora" no Peek se necessário.

### Pago alfândega numa encomenda Hermes vinda da Alemanha?

Não em Portugal: mercadorias expedidas da Alemanha circulam na União Europeia sem direitos nem desalfandegamento. Taxas aduaneiras aplicam-se apenas a envios vindos de fora da UE.

:::sources
- [Otto Group: Konzernfirmen](https://www.ottogroup.com/de/ueber-uns/konzernfirmen/hermes-germany.php) – grupo Otto
- [OTTO: Lieferung](https://www.otto.de/service/lieferung/), [FAQ](https://www.otto.de/service/faq/) e [Live-Tracking](https://www.otto.de/shoppages/geographische_sendungsverfolgung) – prazos, *WunschZustellung*, 17 000 *PaketShops* (7 dias úteis, identificação) e equipas de dois
- [Evri: About us](https://www.evri.com/about-us) – transição da Hermes UK para Evri (março de 2022)
- [Bundesnetzagentur: Schlichtungsstelle Post](https://www.bundesnetzagentur.de/DE/Vportal/Schlichtung/Schlichtung_Post/start.html) – conciliação postal gratuita
- [BGB: § 475](https://www.gesetze-im-internet.de/bgb/__475.html), [§ 355](https://www.gesetze-im-internet.de/bgb/__355.html), [§ 356](https://www.gesetze-im-internet.de/bgb/__356.html) e [HGB: § 421](https://www.gesetze-im-internet.de/hgb/__421.html), [§ 438](https://www.gesetze-im-internet.de/hgb/__438.html) – garantia, resolução e prazos de transporte
- [Your Europe: Entrega](https://europa.eu/youreurope/citizens/consumers/shopping/shipping-delivery/index_pt.htm), [Portal das Finanças: Encomendas postais](https://info.portaldasfinancas.gov.pt/pt/apoio_ao_contribuinte/Cidadaos/Compras/Encomendas_postais/Encomendas_Postais_Comercio_Eletronico/Paginas/default.aspx), [EU Customs Reform](https://taxation-customs.ec.europa.eu/customs/eu-customs-reform_en) e [Guidance 3 €](https://taxation-customs.ec.europa.eu/document/download/053e5b4e-f0be-4f20-9a23-3e3b659a6676_en?filename=Customs%20Guidance%20on%20EUR%203%20customs%20duty.pdf) – circulação UE e reforma 2026
- [CTT: Fim da isenção 150 €](https://www.ctt.pt/particulares/receber/desalfandegar/fim-da-isencao-de-direitos-aduaneiros-em-compras-ate-150-euros-fora-da-uniao-europeia), [Prazo de desalfandegamento](https://www.ctt.pt/ajuda/particulares/importacoes-e-desalfandegamento/prazo-de-desalfandegamento/prazo-para-desalfandegar-a-minha-encomenda) e [Alertas de phishing](https://www.ctt.pt/transversais/alertas-de-phishing) – regras extra-UE e hiperligações `www.ctt.pt`
- [Universal Parcel Scraper: hermes-de](https://github.com/plhery/universal-parcel-scraper/tree/main/carriers/hermes-de) e [statuses.json](https://github.com/plhery/universal-parcel-scraper/blob/main/carriers/hermes-de/statuses.json) – leitura no Peek
:::
