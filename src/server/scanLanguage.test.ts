import { describe, expect, it } from 'vitest';
import { otherLanguages } from './scanLanguage';

describe('otherLanguages', () => {
  it('tells two scripts apart, a Latin place name in the other script\'s row aside', () => {
    expect(otherLanguages('包裹已到达分拣中心', 'Arrived at the sorting center')).toBe(true);
    expect(otherLanguages('包裹已到达 Varese Hub', 'Sorted')).toBe(true);
    expect(otherLanguages('Принято в отделении связи', 'Posted')).toBe(true);
    expect(otherLanguages('荷物を受け付けました', '包裹已收寄')).toBe(true);
    expect(otherLanguages('Принято', 'Прибыло в сортировочный центр')).toBe(false);
  });

  it('tells Latin languages apart only by words no other listed language uses', () => {
    expect(otherLanguages('The package left sorting center', "Colis en cours d'acheminement")).toBe(true);
    expect(otherLanguages('Parcel scanned', 'Registrato presso il centro di smistamento')).toBe(true);
    expect(otherLanguages('Sendung wurde zugestellt', 'Delivered')).toBe(true);
    // Or by letters one of them alone writes, in a word or two.
    expect(otherLanguages('Doręczono', 'Delivered')).toBe(true);
    expect(otherLanguages('Kézbesítő átvette', 'Livré au destinataire')).toBe(true);
    expect(otherLanguages('Küldemény feldolgozás alatt', 'Parcel sorted')).toBe(true);
    // The same language, or words that show none ("In transit", "Hub"), are not two languages.
    expect(otherLanguages('Loaded onto a vehicle', 'Sorted')).toBe(false);
    expect(otherLanguages('In transit', 'Registrato presso il centro di smistamento')).toBe(false);
    expect(otherLanguages('Hub', 'Colis trié')).toBe(false);
    // A row in two languages shows none.
    expect(otherLanguages('Votre colis est livré / Your parcel was delivered', 'Delivered')).toBe(false);
    expect(otherLanguages('', 'Delivered')).toBe(false);
  });
});
