#include <iostream>
#include <algorithm>
#include<string.h>
using namespace std;
typedef long long ll;
ll tree[3000000];
int treeSize;
int start;

void update(int x, ll y) {
	int idx = start + x;
	tree[idx] = y;
	while (idx > 1) {
		if (idx % 2 == 0) {
			tree[idx / 2] = tree[idx] + tree[idx + 1];
		}
		else {
			tree[idx / 2] = tree[idx] + tree[idx - 1];
		}
		idx /= 2;
	}

}

ll getSum(int left, int right) {
	ll sum = 0;
	left += start;
	right += start;
	while (left <= right) {
		if (left % 2 == 1) {
			sum += tree[left++];
		}
		if (right % 2 == 0) {
			sum += tree[right--];
		}
		left /= 2;
		right /= 2;
	}
	return sum;
}

void run() {
	int N, M, K;
	cin >> N >> M >> K;

	treeSize = 1;
	while (treeSize < N) {
		treeSize *= 2;
	}
	memset(tree, 0, treeSize * 2 + 1);
	start = treeSize - 1;
	for (int i = 1; i <= N; i++) {
		cin >> tree[start+i];
	}

	for (int i = start; i != 0; i--) {
		tree[i] = tree[i * 2] + tree[i * 2 + 1];
	}

	for (int i = 0; i < M + K; i++) {
		int a; cin >> a;
		if (a == 1) {
			int b; ll c; cin >> b >> c;
			update(b, c);
		}
		else if (a == 2) {
			int b, c; cin >> b >> c;
			cout << getSum(b, c) << '\n';
		}
	}
}

int main()
{
	cin.tie(0);
	ios::sync_with_stdio(0);
	run();

	return 0;
}