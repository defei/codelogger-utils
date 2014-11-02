package org.codelogger.utils.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AlphabetTest {

    @Test
    public void getInstance() {

        Alphabet A = Alphabet.A;
        assertNotNull(A);
        assertEquals("A", A.getKey());
        assertEquals(2, A.getValue());

        Alphabet B = Alphabet.B;
        assertNotNull(B);
        assertEquals("B", B.getKey());
        assertEquals(3, B.getValue());

        Alphabet C = Alphabet.C;
        assertNotNull(C);
        assertEquals("C", C.getKey());
        assertEquals(5, C.getValue());

        Alphabet D = Alphabet.D;
        assertNotNull(D);
        assertEquals("D", D.getKey());
        assertEquals(7, D.getValue());

        Alphabet E = Alphabet.E;
        assertNotNull(E);
        assertEquals("E", E.getKey());
        assertEquals(11, E.getValue());

        Alphabet F = Alphabet.F;
        assertNotNull(F);
        assertEquals("F", F.getKey());
        assertEquals(13, F.getValue());

        Alphabet G = Alphabet.G;
        assertNotNull(G);
        assertEquals("G", G.getKey());
        assertEquals(17, G.getValue());

        Alphabet H = Alphabet.H;
        assertNotNull(H);
        assertEquals("H", H.getKey());
        assertEquals(19, H.getValue());

        Alphabet I = Alphabet.I;
        assertNotNull(I);
        assertEquals("I", I.getKey());
        assertEquals(23, I.getValue());

        Alphabet J = Alphabet.J;
        assertNotNull(J);
        assertEquals("J", J.getKey());
        assertEquals(29, J.getValue());

        Alphabet K = Alphabet.K;
        assertNotNull(K);
        assertEquals("K", K.getKey());
        assertEquals(31, K.getValue());

        Alphabet L = Alphabet.L;
        assertNotNull(L);
        assertEquals("L", L.getKey());
        assertEquals(37, L.getValue());

        Alphabet M = Alphabet.M;
        assertNotNull(M);
        assertEquals("M", M.getKey());
        assertEquals(41, M.getValue());

        Alphabet N = Alphabet.N;
        assertNotNull(N);
        assertEquals("N", N.getKey());
        assertEquals(43, N.getValue());

        Alphabet O = Alphabet.O;
        assertNotNull(O);
        assertEquals("O", O.getKey());
        assertEquals(47, O.getValue());

        Alphabet P = Alphabet.P;
        assertNotNull(P);
        assertEquals("P", P.getKey());
        assertEquals(53, P.getValue());

        Alphabet Q = Alphabet.Q;
        assertNotNull(Q);
        assertEquals("Q", Q.getKey());
        assertEquals(59, Q.getValue());

        Alphabet R = Alphabet.R;
        assertNotNull(R);
        assertEquals("R", R.getKey());
        assertEquals(61, R.getValue());

        Alphabet S = Alphabet.S;
        assertNotNull(S);
        assertEquals("S", S.getKey());
        assertEquals(67, S.getValue());

        Alphabet T = Alphabet.T;
        assertNotNull(T);
        assertEquals("T", T.getKey());
        assertEquals(71, T.getValue());

        Alphabet U = Alphabet.U;
        assertNotNull(U);
        assertEquals("U", U.getKey());
        assertEquals(73, U.getValue());

        Alphabet V = Alphabet.V;
        assertNotNull(V);
        assertEquals("V", V.getKey());
        assertEquals(79, V.getValue());

        Alphabet W = Alphabet.W;
        assertNotNull(W);
        assertEquals("W", W.getKey());
        assertEquals(83, W.getValue());

        Alphabet X = Alphabet.X;
        assertNotNull(X);
        assertEquals("X", X.getKey());
        assertEquals(89, X.getValue());

        Alphabet Y = Alphabet.Y;
        assertNotNull(Y);
        assertEquals("Y", Y.getKey());
        assertEquals(97, Y.getValue());

        Alphabet Z = Alphabet.Z;
        assertNotNull(Z);
        assertEquals("Z", Z.getKey());
        assertEquals(101, Z.getValue());
    }

    @Test
    public void valueOf() {

        Alphabet alphabetA = Alphabet.valueOf("A");
        assertEquals(Alphabet.A, alphabetA);

        Alphabet alphabetB = Alphabet.valueOf("B");
        assertEquals(Alphabet.B, alphabetB);

        Alphabet alphabetC = Alphabet.valueOf("C");
        assertEquals(Alphabet.C, alphabetC);

        Alphabet alphabetD = Alphabet.valueOf("D");
        assertEquals(Alphabet.D, alphabetD);

        Alphabet alphabetE = Alphabet.valueOf("E");
        assertEquals(Alphabet.E, alphabetE);

        Alphabet alphabetF = Alphabet.valueOf("F");
        assertEquals(Alphabet.F, alphabetF);

        Alphabet alphabetG = Alphabet.valueOf("G");
        assertEquals(Alphabet.G, alphabetG);

        Alphabet alphabetH = Alphabet.valueOf("H");
        assertEquals(Alphabet.H, alphabetH);

        Alphabet alphabetI = Alphabet.valueOf("I");
        assertEquals(Alphabet.I, alphabetI);

        Alphabet alphabetJ = Alphabet.valueOf("J");
        assertEquals(Alphabet.J, alphabetJ);

        Alphabet alphabetK = Alphabet.valueOf("K");
        assertEquals(Alphabet.K, alphabetK);

        Alphabet alphabetL = Alphabet.valueOf("L");
        assertEquals(Alphabet.L, alphabetL);

        Alphabet alphabetM = Alphabet.valueOf("M");
        assertEquals(Alphabet.M, alphabetM);

        Alphabet alphabetN = Alphabet.valueOf("N");
        assertEquals(Alphabet.N, alphabetN);

        Alphabet alphabetO = Alphabet.valueOf("O");
        assertEquals(Alphabet.O, alphabetO);

        Alphabet alphabetP = Alphabet.valueOf("P");
        assertEquals(Alphabet.P, alphabetP);

        Alphabet alphabetQ = Alphabet.valueOf("Q");
        assertEquals(Alphabet.Q, alphabetQ);

        Alphabet alphabetR = Alphabet.valueOf("R");
        assertEquals(Alphabet.R, alphabetR);

        Alphabet alphabetS = Alphabet.valueOf("S");
        assertEquals(Alphabet.S, alphabetS);

        Alphabet alphabetT = Alphabet.valueOf("T");
        assertEquals(Alphabet.T, alphabetT);

        Alphabet alphabetU = Alphabet.valueOf("U");
        assertEquals(Alphabet.U, alphabetU);

        Alphabet alphabetV = Alphabet.valueOf("V");
        assertEquals(Alphabet.V, alphabetV);

        Alphabet alphabetW = Alphabet.valueOf("W");
        assertEquals(Alphabet.W, alphabetW);

        Alphabet alphabetX = Alphabet.valueOf("X");
        assertEquals(Alphabet.X, alphabetX);

        Alphabet alphabetY = Alphabet.valueOf("Y");
        assertEquals(Alphabet.Y, alphabetY);

        Alphabet alphabetZ = Alphabet.valueOf("Z");
        assertEquals(Alphabet.Z, alphabetZ);
    }

    @Test
    public void values() {

        int expectedSize = 26;
        Alphabet[] values = Alphabet.values();
        assertEquals(expectedSize, values.length);
    }
}
