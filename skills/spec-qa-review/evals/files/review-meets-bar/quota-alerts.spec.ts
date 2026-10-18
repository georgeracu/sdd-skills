import { test, expect } from '@playwright/test';

test('owner replaces thresholds and reads them back', async ({ request }) => {
  const put = await request.put('/accounts/acc_abcdefghij01/thresholds', { data: { thresholds: [50, 90] } });
  expect(put.status()).toBe(200);
  const get = await request.get('/accounts/acc_abcdefghij01/thresholds');
  expect(await get.json()).toEqual({ thresholds: [50, 90] });
});

test('an invalid list is rejected and the stored set is unchanged', async ({ request }) => {
  const put = await request.put('/accounts/acc_abcdefghij01/thresholds', { data: { thresholds: [50, 50] } });
  expect(put.status()).toBe(400);
  expect((await put.json()).code).toBe('threshold-set-invalid');
  const get = await request.get('/accounts/acc_abcdefghij01/thresholds');
  expect(await get.json()).toEqual({ thresholds: [50, 90] });
});
