package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uusiVarastoEiNegatiivisellaTilavuudella() {
        Varasto varasto2 = new Varasto(-10);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiMuuta() {
        varasto.lisaaVarastoon(-2);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylimaarainenLisaysHukkaan() {
        varasto.lisaaVarastoon(20);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiPoistuNegatiivinen() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-2);

        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastostaEiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(8);

        assertEquals(8, varasto.otaVarastosta(10), vertailuTarkkuus);
    }

    @Test
    public void toStringReturnsRightString() {
        varasto.lisaaVarastoon(8);

        String oikea = "saldo = 8.0, vielä tilaa 2.0";

        assertEquals(oikea, varasto.toString());
    }

    @Test
    public void toinenKonstruktoriSaldoOikein() {
        Varasto varasto2 = new Varasto(10, 2);

        assertEquals(2, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toinenKonstruktoriTilavuusOikein() {
        Varasto varasto2 = new Varasto(10, 2);

        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toinenKonstruktoriHoitaaNegatiivisenSaldon() {
        Varasto varasto2 = new Varasto(10, -2);

        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toinenKonstruktoriHoitaaNegatiivisenTilavuuden() {
        Varasto varasto2 = new Varasto(-10, 0);

        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void toinenKonstruktoriHoitaaYlisuurenSaldon() {
        Varasto varasto2 = new Varasto(10, 20);

        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
}